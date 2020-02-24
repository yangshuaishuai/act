SELECT  * from  act.ACT_RU_TASK -- 运行中的任务

select * from act.ACT_RU_EXECUTION -- 正在执行的流程对象

select * from act.ACT_RE_DEPLOYMENT

 
select *  from act.ACT_RE_PROCDEF

select * from act.ACT_HI_ACTINST -- 所有的活动节点

select * from act.ACT_HI_TASKINST -- 所有的历史活动节点

-- 流程变量
select * from  act.ACT_RU_VARIABLE -- 流程变量 

select * from act.ACT_HI_VARINST -- 历史的流程变量 

select * from  act.ACT_GE_BYTEARRAY -- 资源表
##################################
select * from   act.ACT_RU_IDENTITYLINK -- 任务表 包括（组任务和个人任务） participant 表示个人任务  candidate表示组任务

select * from act.ACT_HI_IDENTITYLINK -- 历史任务表 包括（组任务和个人任务）

