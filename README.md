

## 【文档】

Avalanche（雪崩）是一款用于汽车保养的记录与查询工具。

### Api

· UI层 <br>

> Package：org.zhangyibin.avalanche;<br>
> · Avalanche(雪崩的启动入口);

· 项目保养 <br>

> Package：org.zhangyibin.maintenanceitem;

| -class-                      | -Type-                       | -Method-                                                                                                           | -Description-                           |
|------------------------------|------------------------------|--------------------------------------------------------------------------------------------------------------------|-----------------------------------------|
| -EnterMaintenanceRecords     | -Boolean                     | -onEnterMaintenanceRecords( <br/>describe(描述)- <br/>productParameter(所保养的产品名称)-<br/>time(保养时间)-<br/>mileage(保养里程)) | -保养记录接口                                 |
| -MaintenanceInformationQuery | -MaintenanceInformationQuery | -getMaintenanceInformationQuery( <br/>currentMileage(当前里程数据为必填项) <br/>)                                            | -保养信息查询<br/>*单例模式                       |
| -MaintenanceInformationQuery | -String                      | -onMaintenanceInformationQuery()                                                                                   | -根据当前输入的里程数据和上一次的保养里程相减，而得出的本次保养所需维护的项目 |
| -WaitingMaintenanceItem      | -WaitingMaintenanceItem      | -getWaitingMaintenanceItem()                                                                                       | -下一次所需保养的项目<br/>*单例模式                   |
| -WaitingMaintenanceItem      | -String                      | -isChangeTires()                                                                                                   | -是否跟换轮胎                                 |
| -WaitingMaintenanceItem      | -String                      | -isChangeReplaceAirFilter()                                                                                        | -是否更换空气滤芯                               |
| -WaitingMaintenanceItem      | -String                      | -isChangeAirConditioningFilter()                                                                                   | -是否更换空调滤芯                               |
| -WaitingMaintenanceItem      | ---                          | -setCurrentMileage(<br/>currentMileage(里程数)<br/>)                                                                  | -设置当前里程数                                |
| -WaitingMaintenanceItem      | -int                         | -getCurrentMileage()                                                                                               | -返回当前里程                                 |
| -WaitingMaintenanceItem      | -String                      | -isChangeGearboxOil()                                                                                              | -是否更换变速箱油                               |
| -WaitingMaintenanceItem      | -String                      | -isChangeAntifreeze()                                                                                              | -是否更换防冻液                                |
| -WaitingMaintenanceItem      | -String                      | -isChangeSparkPlug()                                                                                               | -是否更换火花塞                                |
| -WaitingMaintenanceItem      | -String                      | -isChangeGasolineFilter()                                                                                          | -是否更换汽油滤芯                               |
| -WaitingMaintenanceItem      | -String                      | -isChangeBrakeFluid()                                                                                              | -是否更换刹车油                                |
| -WaitingMaintenanceItem      | -String                      | -isChangeEngineOil()                                                                                               | -是否更换机油                                 |

· 服务层 <br>
> Package：org.zhangyibin.service;

| -class-        | -Type-               | -Method-                                    | -Description-                                               |
|----------------|----------------------|---------------------------------------------|-------------------------------------------------------------|
| -PoloParameter | -String              | -toDate()                                   | -获取当前系统时间                                                   |
| -PoloParameter | ---                  | -setMileage(<br/> mileage(里程数)<br/>)        | -设置当前里程数                                                    |
| -PoloParameter | -Integer             | -toMileage()                                | -返回当前里程数                                                    |
| -ProductEnum   | -String              | - getProductEnum()                          | -保养产品枚举类；包括：轮胎、 空气滤芯、 空调滤芯、变速箱油、 防冻液、 火花塞、汽油滤芯、 刹车油、机油<br/> |
| -Report        | -Map<String, Integer> | - onProduct(<br/>type(时间/里程类型)<br/>)        | -根据产品类型查询对应产品的保养规则                                          |
| -Report        | -Map<String, String> | -onNearestdate(<br/>productName(产品名称)<br/>) | -根据保养产品名称查询最近一次的保养日期                                        |
| -Report        | -Map<String, Integer> | -onMileage(<br/>productName(产品名称)<br/>)     | -根据保养产品名称查询最近一次的保养里程                                        |
| -Rule          | -Map<String, Integer> | -getTimeProduct()                           | -返回时间维度的产品规则；<br>时间维度的产品包括：轮胎、空气滤芯、空调滤芯                     |
| -Rule          | -Map<String, Integer> | -getMileageProduct()                        | -返回里程维度的产品规则；<br>    时间维度的产品包括：变速箱油、防冻液、火花塞、汽油滤芯、刹车油、机油     |
| -TypeEnum      | -String              | -getTypeEnum()                              | -保养产品维度枚举类；<br/>        维度包括：时间和里程                          |
| -QueryHistory      | ---               | -setWriteFile(上次查询数据)                       | -缓存上次查询的里程数据                          |
| -QueryHistory      | -String              | -getReadFile()                              | -返回上次查询的里程数据                          |

· 工具层 <br>

> Package：org.zhangyibin.util;

| -class-             | -Type-       | -Method-                                                    | -Description-          |
|---------------------|--------------|-------------------------------------------------------------|------------------------|
| -DBUtil             | - Connection | -getConnection()                                            | -用于链接数据库；              |
| -ImageUtil          | -ImageIcon   | -getImageIcon(<br/> imgName(图片名称))                          | -返回图片信息                |
| -Intervals          | -long        | -onIntervals(<br/> dateStart(当前日期)<br/>dateStop(历史日期)<br/>) | -计算当前时间距离上一次保养时间的间隔天数  |
| -StringConvertArray | -String[][]  | -getStringConvertArray(String json)                         | -字符串转换数组，主要用于工具首页的表格展示 |

· 测试类 <br>

> Package：test; <br>
> · EnterMaintenanceRecordsTest; <br>
> · MaintenanceInformationQueryTest; <br>
> · MaintenanceItemTest; <br>
> · ReportTest; <br>
> · StringConvertArrayTest; <br>


