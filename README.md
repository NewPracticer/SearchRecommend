# SearchRecommend

#### 介绍
搜索推荐样例

#### 架构图
Structure为架构图

#### 项目说明
1. tmdb 作为 数据源，训练es的使用
2. 搜索以及推荐部分


#### Tmdb项目中的es使用
1.  索引建立
2.  match查询
	1. 按照字段上定义的分词分析后去索引内查询
	2. match分词后的and和or
	3. 最小词项匹配
3. term查询，不进行词的分析，直接去索引查询，及搜索关键词和索引内词的精确匹配
4. 短语查询，短语前缀词查询
5. 多字段查询
	1. 加权重的多字段
6.  布尔查询
	1. must：必须是true
	2. must not :必须是false
	3. should：其中有一个为true即可
7. 不同类型查询
	1. best_fields:默认，取得分最高的作为对应的分数，最匹配模式,
	2. most_fields:取命中的分值相加作为分数，同should match模式，加权共同影响模式
	3. cross_fields:以分词为单位计算栏位总分
8. filter查询
	1. 单条件过滤
	2. 多条件过滤
	3. 带match的filter
9. function 自定义打分
	1. field：声明对应要处理的字段
	2. modifier：计算对数
	3. factor ：影响因子，预处理
	4. score_mode：不同的field value之间的得分相加
	5. boost_mode：最后在与old value相加
 
#### es 搜索进阶
1. IK分词器自定义
	1. 热更新词库 
2. 同义词及相关处理
	2. 更改搜索条件以及方式
3. 索引实时性
	1. 原来使用logstash,第一次全量构建索引，然后每分钟执行sql，进行增量索引更新。
	2. canal 引入

#### 推荐系统
1. 使用ALS算法对离线数据进行模型训练，通过用户信息和产品信息的矩阵相乘，获取相关的信息
2. 使用LR 和 GBDT算法 对离线数据进行进行模型预测，并打分，实现召回的精排。

#### 后续迭代
1. 构建用户画像


