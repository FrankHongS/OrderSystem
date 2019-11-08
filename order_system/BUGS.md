# Bugs

* 更新和新增order成功后，刷新时页面会跳转到第1页；
* 删除order时，没有将与order相关联的postsold和recentDate一并删除；
* 打开多个售后细节页面时，由于将selectedOrder存于localStorage的同一个变量，会存在覆盖的情况