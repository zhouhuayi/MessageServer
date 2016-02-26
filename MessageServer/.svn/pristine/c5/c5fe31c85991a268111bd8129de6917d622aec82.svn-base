package com.choose.Message.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis缓存服务器公用类
 * 
 * @version 1.0
 * @author 周化益
 * @since 2016-02-15
 */
public class Redis {
	//创建连接池
	private static JedisPool pool;
	
	//Redis 连接地址
	private static String ip = "192.168.0.11";
	
	//Redis 连接端口号
	private static int port = 6379;
	
	//Redis 连接密码
	private static String pwd = "zhy123";
	
	//客户端Jedis对象
	private static Jedis jedis;
	
	//开启定时任务
	public static Timer timer;
	
	//初始化返回结果
	private static Boolean bool = false;
	
	//初始化返回值
	private static String value = "";
	
	//初始化 结果数
	private static long num = 0;
	
    //获取当前时间
	private static Date date = new Date();
	
	/**
	 * 获取连接池
	 * 
	 * @author 周化益
	 * @param ip IP地址
	 * @param port 端口号
	 * @return JedisPool
	 */
	public static JedisPool getPool() {
		if (pool == null) {
			JedisPoolConfig config = new JedisPoolConfig();
			//连接池最大使用连接数量
			config.setMaxTotal(500);
			//连接池最大空闲 
			config.setMaxIdle(5);
			//连接等待时间
			config.setMaxWaitMillis(1000 * 100);
			//是否允许浏览器测试
			config.setTestOnBorrow(true);
			//初始化连接池
			pool = new JedisPool(config, ip, port, 0, pwd);
		}
		return pool;
	}

	/**
	 * 释放连接资源
	 * 
	 * @author 周化益
	 * @param pool
	 * @param redis
	 */
	public static void returnResource(JedisPool pool, Jedis redis) {
		if (redis != null) {
			pool.returnResource(redis);
		}
	}

	/**
	 * 返回jedis
	 * 
	 * @param key
	 * @return
	 */
	public static Jedis getJedis() {
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
		} catch (Exception e) {
			// 获取redis连接池
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 释放连接
			returnResource(pool, jedis);
		}
		return jedis;
	}
	
	/**
	 * Redis key用法
	 */
	public static void testRedisKey() {
		System.err.println("===================key测试开始===================");
		
		//存储单个值
		value = jedis.set("w3ckey", "redis");
		if(value == null || value.trim().equals("")) {
			System.out.println("添加失败！");
		} else {
			System.out.println("添加成功！");
		}
		
		//模糊查询w开头的所有key
		System.out.println(jedis.keys("w*"));
		
		//判断jedis是否存在key
		bool = jedis.exists("w3ckey");
		
		if(bool) {
			System.out.println("存在改key！");
		} else {
			System.out.println("不存在该key！");
		}
		
		//删除单个key
		num = jedis.del("w3ckey");
		
		if(num > 0) {
			System.out.println("删除成功！");
		} else {
			System.out.println("删除失败！");
		}
		
		//模糊查询w开头的所有key
		System.out.println(jedis.keys("w*"));
		
		//存储单个值
		jedis.set("greeting", "redis");
		
		//获取key对应的值
		System.out.println(jedis.get("greeting"));
		
		//序列化key
		value = jedis.dump("greeting").toString();
		
		//打印序列化后的值
		System.out.println(value);
		
		//设置过去时间以秒计算
		jedis.expire("greeting", 30);
		timer = new Timer();
		//在1秒后执行此任务,每次间隔2秒执行一次,如果传递一个Data参数,就可以在某个固定的时间执行这个任务.
        timer.schedule(new MyTask(), 1000, 2000);  
        
        /*Expireat 命令 - EXPIREAT 的作用和 EXPIRE 类似，都用于为 key 设置过期时间。 
         * 不同在于 EXPIREAT 命令接受的时间参数是 UNIX 时间戳(unix timestamp)。 设置20秒后过期 */
        jedis.expireAt("greeting", date.getTime()/1000 + 20);
        
        //Redis PEXPIREAT 命令 - 设置 key 的过期时间亿以毫秒计。  设置5秒后过期
        jedis.pexpireAt("greeting", date.getTime() + 5*1000);
        
        //redis默认使用数据库 0，为了清晰起见，这里再显式指定一次
        jedis.select(0);
        
        jedis.set("song", "secret base - Zone");
        
        //将 song 移动到数据库 1
        jedis.move("song", 1);
        
        bool = jedis.exists("song");
        if(bool) {
			System.out.println("数据库0存在改key！");
		} else {
			System.out.println("数据库0不存在该key！");
		}
        
        //指定数据库
        jedis.select(1);
        
        bool = jedis.exists("song");
        if(bool) {
			System.out.println("数据库1存在改key！");
		} else {
			System.out.println("数据库1不存在该key！");
		}
        
        //Redis PERSIST 命令 - 移除 key 的过期时间，key 将持久保持。
        num = jedis.persist("greeting");
        if(num > 0) {
			System.out.println("移除成功！");
		} else {
			System.out.println("移除失败！");
		}
        
        //Redis RANDOMKEY 命令 - 从当前数据库中随机返回一个 key 。
        System.out.println(jedis.randomKey());
        
        //Redis Renamenx 命令 - 仅当 newkey 不存在时，将 key 改名为 newkey 。
        System.out.println(jedis.renamenx("song", "songs"));
        
        //Redis Type 命令 - 返回 key 所储存的值的类型。
        System.out.println(jedis.type("songs"));
        
        System.err.println("===================key测试结束===================");
	}
	
	/**
	 * Redis String用法
	 */
	public static void testRedisString() {
		System.err.println("===================String测试开始===================");
		//设置指定 key 的值
		jedis.set("key", "value");
		
		//获取指定 key 的值。
		System.out.println(jedis.get("key"));
		
		//Getrange 命令 - 返回 key 中字符串值的子字符 ,0-1查询所有
		System.out.println(jedis.getrange("key", 0, 2));
		
		//Getset 命令 - 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。
		System.out.println(jedis.getSet("key", "newValue"));
		
		//Getbit 命令 - 对 key 所储存的字符串值，获取指定偏移量上的位(bit)。
		jedis.setbit("bit", 10010, true);
		System.out.println(jedis.getbit("bit", 10010));
		
		//Mget 命令 - 获取所有(一个或多个)给定 key 的值。
		jedis.set("key1", "value1");
		System.out.println(jedis.mget("key","key1"));
		
		//Setex 命令 - 将值 value 关联到 key ，并将 key 的过期时间设为 seconds (以秒为单位)。
		System.out.println(jedis.setex("setex", 10, "setex11"));
		
		//Setnx 命令 - 只有在 key 不存在时设置 key 的值。
		System.out.println(jedis.setnx("key", "value"));
		if(jedis.exists("key0")) {
			jedis.del("key0");
		}
		System.out.println(jedis.setnx("key0", "value0"));
		
		//Setrange 命令 - 用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始。
		jedis.set("mykey", "Hello World");
		jedis.setrange("mykey", 6, "Redis");
		System.out.println(jedis.get("mykey"));
		
		//Strlen 命令 - 返回 key 所储存的字符串值的长度。
		System.out.println(jedis.strlen("mykey"));

		//Mset 命令 - 同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在。
		jedis.mset("key2","vlaue2","key3","vlaue3","key4","vlaue4","key5","vlaue5");
		System.out.println(jedis.mget("key","key1","key2","key3","key4","key5"));
		
		//Msetnx 命令 - 同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在。
		System.out.println(jedis.msetnx("key2","vlaue2","key3","vlaue3","key4","vlaue4","key5","vlaue5"));
		
		//Psetex 命令 - 这个命令和 SETEX 命令相似，但它以毫秒为单位设置 key 的生存时间，而不是像 SETEX 命令那样，以秒为单位。
		System.out.println(jedis.psetex("key6", 10000, "value6"));
		
		//Incr 命令 - 将 key 中储存的数字值增一。
		jedis.set("key7", "1");
		System.out.println(jedis.incr("key7"));
		
		//Incrby 命令 - 将 key 所储存的值加上给定的增量值（increment） 。
		System.out.println(jedis.incrBy("key7",10));
		
		//Incrbyfloat 命令 - 将 key 所储存的值加上给定的浮点增量值（increment） 。
		System.out.println(jedis.incrByFloat("key7",1.01));
		
		//Decr 命令 - 将 key 中储存的数字值减一。
		jedis.set("key8", "15");
		System.out.println(jedis.decr("key8"));
		
		//Decrby 命令 - key 所储存的值减去给定的减量值（decrement） 。
		System.out.println(jedis.decrBy("key8",10));
		
		//Append 命令 - 如果 key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾。
		jedis.set("key9", "value");
		jedis.append("key9", "9");
		System.out.println(jedis.get("key9"));
		
		System.err.println("===================String测试结束===================");
	}
	
	/**
	 * Redis Hash用法
	 */
	public static void testRedisHash() {
		System.err.println("===================Hash测试开始===================");
		
		//Hset 命令 - 将哈希表 key 中的字段 field 的值设为 value 。
		jedis.hset("map1", "mapKey1", "mapValue1");
		
		//Hsetnx 命令 - 只有在字段 field 不存在时，设置哈希表字段的值。
		System.out.println(jedis.hsetnx("map1", "mapKey1", "mapValue2"));
		System.out.println(jedis.hsetnx("map1", "mapKey2", "mapValue2"));
		
		//Hmset 命令 - 同时将多个 field-value (域-值)对设置到哈希表 key 中。
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("account", "admin");
		map2.put("password", "123");
		map2.put("userId", "1");
		jedis.hmset("map2", map2);
		
		//Hexists 命令 - 查看哈希表 key 中，指定的字段是否存在。
		System.out.println(jedis.hexists("map1", "mapKey1"));
		
		//Hkeys 命令 - 获取所有哈希表中的字段
		System.out.println(jedis.hkeys("map2"));
		
		//Hvals 命令 - 获取哈希表中所有值
		System.out.println(jedis.hvals("map2"));
		
		//Hget 命令 - 获取存储在哈希表中指定字段的值/td>
		System.out.println(jedis.hget("map1", "mapKey1"));
		
		//Hmget 命令 - 获取所有给定字段的值
		System.out.println(jedis.hmget("map2", "account","password"));
		
		//Hgetall 命令 - 获取在哈希表中指定 key 的所有字段和值
		System.out.println(jedis.hgetAll("map2"));
		
		//Hincrby 命令 - 为哈希表 key 中的指定字段的整数值加上增量 increment 。
		System.out.println(jedis.hincrBy("map2", "password", 4));
		
		//Hincrbyfloat 命令 - 为哈希表 key 中的指定字段的浮点数值加上增量 increment 。
		System.out.println(jedis.hincrByFloat("map2", "password", 0.0));
		
		//Hlen 命令 - 获取哈希表中字段的数量
		System.out.println(jedis.hlen("map2"));
		
		//hdel 命令 - 获取哈希表中的字段
		System.out.println(jedis.hdel("map1", "mapKey2"));
		
		System.err.println("===================Hash测试结束===================");
	}
	
	/**
	 * Redis List用法
	 */
	public static void testRedisList() {
		System.err.println("===================List测试开始===================");
		
		//Rpush 命令 - 在列表中添加一个或多个值
//		jedis.rpush("list1", "firstList1", "firstList2", "firstList3", "firstList4", "firstList5");
//		System.out.println(jedis.lrange("list1", 0, -1));
		
		//Lset 命令 - 通过索引设置列表元素的值
//		jedis.lset("list1", 1, "value1");
//		System.out.println(jedis.lindex("list1", 0));
//		System.out.println(jedis.lrange("list1", 0, -1));
		
		//Blpop 命令 - 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
//		System.out.println(jedis.blpop("list2"));
//		System.out.println(jedis.lrange("list1", 0, -1));
		
		//Brpop 命令 - 移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
//		System.out.println(jedis.brpop("list1")); 
//		System.out.println(jedis.lrange("list1", 0, -1));
		
		//Brpoplpush 命令 - 从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
		jedis.brpoplpush("list1", "list2", 500);
		
		//Lindex 命令 - 通过索引获取列表中的元素
		System.out.println(jedis.lindex("list1", 1));
		
		//Linsert 命令 - 在列表的元素前或者后插入元素
		System.out.println(jedis.linsert("list1", LIST_POSITION.BEFORE, "firstList1", "firstList0"));
		
		//Llen 命令 - 获取列表长度
		System.out.println(jedis.llen("list1"));
		
		//Lpop 命令 - 移出并获取列表的第一个元素
		System.out.println(jedis.lpop("list1"));
		
		//Lpush 命令 - 将一个或多个值插入到列表头部
		System.out.println(jedis.lpush("list1", "insert1","insert2","insert3"));
		
		//Lrange 命令 - 获取列表指定范围内的元素
		System.out.println(jedis.lrange("list1", 0, -1));
		
		/*Lrem 命令 - 移除列表元素
		 * count > 0 : 从表头开始向表尾搜索，移除与 VALUE 相等的元素，数量为 COUNT 。
	     * count < 0 : 从表尾开始向表头搜索，移除与 VALUE 相等的元素，数量为 COUNT 的绝对值。
	     * count = 0 : 移除表中所有与 VALUE 相等的值。
		 */
		System.out.println(jedis.lrem("list1", 0, "insert1"));
		
		// Ltrim 命令 - 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
		System.out.println(jedis.ltrim("list1", 5, 10));
		
		//Rpop 命令 - 移除并获取列表最后一个元素
		System.out.println(jedis.rpop("list1"));
		
		//Rpoplpush 命令 - 移除列表的最后一个元素，并将该元素添加到另一个列表并返回
		System.out.println(jedis.rpoplpush("list1", "list2"));
		
		//Rpushx 命令 - 为已存在的列表添加值
		System.out.println(jedis.rpushx("list1", "hello"));
		
		System.err.println("===================List测试结束===================");
	}
	
	/**
	 * Redis Set用法
	 */
	public static void testRedisSet() {
		System.err.println("===================Set测试开始===================");
		System.out.println(jedis.del("set1"));
		System.out.println(jedis.del("set2"));
		//Sadd 命令 - 向集合添加一个或多个成员
		System.out.println(jedis.sadd("set1", "set1Value1", "set1Value2", "set1Value3", "set1Value4"));
		
		//Redis Scard 命令 - 获取集合的成员数
		System.out.println(jedis.scard("set1"));
		
		//Sdiff 命令 - 返回给定所有集合的差集
		jedis.sadd("set2", "set2Value1", "set2Value2", "set1Value3", "set1Value4");
		System.out.println(jedis.sdiff("set1","set2"));
		
		//Sdiffstore 命令 - 返回给定所有集合的差集并存储在 destination 中
		System.out.println(jedis.sdiffstore("set3", "set1","set2"));
		System.out.println(jedis.smembers("set3"));
		
		//Sinter 命令 - 返回给定所有集合的交集
		System.out.println(jedis.sinter("set1","set2"));
		
		//Sinterstore 命令 - 返回给定所有集合的交集并存储在 destination 中
		System.out.println(jedis.sinterstore("set4", "set1", "set2"));
		System.out.println(jedis.smembers("set4"));
		
		//Sismember 命令 - 判断 member 元素是否是集合 key 的成员
		System.out.println(jedis.sismember("set1", "set1Value1"));
		
		//Smembers 命令 - 返回集合中的所有成员
		System.out.println(jedis.smembers("set4"));

		//Smove 命令 - 将 member 元素从 source 集合移动到 destination 集合
		System.out.println(jedis.smove("set3", "set4", "set1Value2"));
		
		//Spop 命令 - 移除并返回集合中的一个随机元素
		System.out.println(jedis.spop("set4"));
		
		//Srandmember 命令 - 返回集合中一个或多个随机数
		System.out.println(jedis.srandmember("set4"));
		
		//Srem 命令 - 移除集合中一个或多个成员
		System.out.println(jedis.srem("set4", "set1Value4"));
		
		//Sunion 命令 - 返回所有给定集合的并集
		System.out.println(jedis.sunion("set1", "set2", "set3"));
		
		//Sunionstore 命令 - 所有给定集合的并集存储在 destination 集合中
		System.out.println(jedis.sunionstore("set5", "set1", "set2", "set3","set4"));
		
		//Sscan 命令 - 迭代集合中的元素
		List<String> sr = jedis.sscan("set5", "1").getResult();
		
		for (String string : sr) {
			System.out.println(string);
		}
		
		System.err.println("===================Set测试结束===================");
	}
	
	/**
	 * main方法测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//获取jedis
		jedis = getJedis();
		
//		testRedisKey();
		
//		testRedisString();
		
//		testRedisHash();
		
//		testRedisList();
		
		testRedisSet();
	}
}

class MyTask extends java.util.TimerTask {
    public void run() {
    	Jedis jedis = Redis.getJedis();
        if(jedis.exists("greeting")) {
        	System.out.println(Redis.getJedis().get("greeting")); 
        	
        	//Redis TTL 命令 - 以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)。
        	System.out.println(jedis.ttl("greeting"));
        	
        	//Redis Pttl 命令 - 以毫秒为单位返回 key 的剩余的过期时间。
        	System.out.println(jedis.pttl("greeting"));
        } else {
        	cancel();
        	System.out.println("取消定时任务");
        	Redis.timer.cancel();
        	System.out.println("取消定时器");
        }
    }
}  
