## This is an index project to organize algorithms and technical functions that I seen or met

#### 2017.06.28 Add SnowflakeIdGenerator  

snowflake是twitter开源的分布式ID生成算法

snowflake的结构如下(每部分用-分开):  
  
0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000  
  
第一位为未使用，接下来的41位为毫秒级时间(41位的长度可以使用69年)，然后是5位data center id和5位worker id(10位的长度最多支持部署1024个节点） ，最后12位是毫秒内的计数（12位的计数顺序号支持每个节点每毫秒产生4096个ID序号）

一共加起来刚好64位，为一个Long型。(转换成字符串长度为18)  
  
snowflake 生成的ID整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞（由datacenter和workerId作区分），并且效率较高。据说：snowflake每秒能够产生26万个ID。