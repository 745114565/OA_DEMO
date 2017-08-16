# Spring boot + Spring data + Spring security + redis + quartz + POI +Ztree  技术实现DEMO
       
       security实现登陆赋权并运用sec:authorize控制标签(标签例子在home.html)
       
       redis利用注解@Cacheable实现控制,相关实现在UserServiceImpl里面(也可用在Controller上,其他地方也行)
       配置好redis相关配置和写一个Config类就可以用了.
       
       quartz实现定时任务(动态设置,比如从数据库中取出来)
       
       POI将数据库信息生成excel并导出(
       调用test里面的ExportExcelTest的test方法
       会在/Users/ho/Desktop/excel_test文件下里面
       生成一个out.xls文件,如果文件已存在会修改内容,
       xx.xls文件的样式模版在resources的excel-templates指定
       即单元格大小等等可先自定义一个空的xls文件以作模版)
       
       自定义注解(例子在ExcelResources)
       
       SpringData PagingAndSortingRepository(tools封装了一下) 分页
       
       Ztree实现页面在test和test2(test是请求后台我生成的树,test2是zTree的Demo)
       
        
       
