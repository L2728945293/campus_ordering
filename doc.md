## 用户端需求
### 注册与登录
8071
- 注册
/user/register
method: post
params: {
    "username": ,
    "password": ,
    "nickname": ,
    "phone": ,
    "avatar: 
}
返回User json

- 登录
/user/login
method: post
params: {
    "username": ,
    "password": 
}
返回User json

### 个人中心
#### 获取用户信息
/user/info/{id}
method: get
返回User json

#### 修改用户信息
/user/update
method: put
params: 修改后的User json
返回User json

#### 收货地址管理
- 查看当前用户所有地址
    /user/address/user/{userId}
    method: get
    返回Address json(多个)
- 创建新地址
    /user/address/create
    method: post
    params: {
        user_id,
        name,
        phone,
        address,
        is_default
    } json
    返回Address json
- 修改地址
    /user/address/update
    method: put
    params: 修改后的Address json
    返回Address json
- 删除地址
    /user/address/delete
    method: delete
    params: 要删除的Address json
    返回成功或失败



### 订单
8081
#### 订单管理
- 创建订单
    /order/create
    method: post
    params: {
        userId;
        shopId;
        addressId;
        totalPrice;
        status; // Use Integer
        remark;
    }
    返回Order json
- 修改订单（）
    /order/update
    method: put
    params: 修改后的Order json
    返回Order json
- 查看订单（通过订单号）
    /order/{id}
    method: get
    返回Order json
- 查看订单（通过userId）
    /order/user/{userId}
    method: get
    返回Order json(多个)
- 查看订单（通过shopId）
    /order/shop/{shopId}
    method: get
    返回Order json(多个)
- 删除订单
    /order/delete
    method: delete
    params: 要删除的Order json


#### 订单详情
- 查看订单详情（通过订单详情号）
    /order/item/{id}
    method: get
    返回OrderItem json

- 查看订单详情（通过订单号）
    /order/item/order/{orderId}
    method: get
    返回OrderItem json

- 创建订单详情
    /order/item/create
    method: post
    params: {
        orderId;
        dishId;
        dishName;
        price;
        quantity;
    }
    返回OrderItem json

- 删除订单详情
    /order/item/delete
    method: delete
    params: 要删除的OrderItem json
    返回成功或失败


### 购物车
8091
- 创建购物车项
    /cart/add
    method: post
    private Integer user_id;
    private Integer shop_id;
    private Integer dish_id;
    private Integer quantity;
    private BigDecimal price;
    返回Cart json

- 修改购物车项
    /cart/update
    method: put
    params: 修改后的Cart json
    返回Cart json

- 删除购物车项
    /cart/delete
    method: delete
    params: 要删除的Cart json
    返回成功或失败

- 查看购物车项
    /cart/{userId}
    method: get
    返回Cart json(多个)

- 下单
    /cart/place
    method: post
    params: {
        address_id,
        user_id
    }
    返回Order(多个) json

- 付款
    /order/checkout/{userId}
    method: get
    返回Order(多个) json

### 管理员
8072
- 登录
    /admin/login
    params: username, password
    method: post
    返回Admin json

- 注册
    /admin/register
    params: username, password
    method: post
    返回Admin json

- 获取所有用户信息
    /admin/user
    method: get
    返回User json(多个)

- 获取所有商家信息
    /admin/shop
    method: get
    返回Shop json(多个)

- 修改用户
    /admin/update/user
    method: put
    params: 修改后的User json
    返回User json

- 修改商家
    /admin/update/shop
    method: put
    params: 修改后的Shop json
    返回Shop json
## 商家端需求
8083
### 注册与登录
注册
    /shop/shop/register
    method:post
    params:{
        shop_name  string
        username   string
        password   string
        logo_url   string
    }
    返回code
登陆
    /shop/shop/login
    method:post
    params:{
        shop_name  string
        username   string
        password   string
    }
    返回：{
        id  Integer
        shop_name string
        username  string
        token     string
    }
### 店铺管理
修改商家信息
    /shop/info/update
    method:put
    params:{
        shop_name  string
        username   string
        password   string
        logo_url   string
    }
    返回code
### 订单管理
查询订单
    /shop/order/{id}
    method:get
    params:id
    返回order josn
修改订单
    /shop/order/update
    method:put
    params:{
        userId;
        shopId;
        addressId;
        totalPrice;
        status; // Use Integer
        remark;
    }
    返回code
保存订单
   /shop/order/save
   method:post
   params:{
        userId;
        shopId;
        addressId;
        totalPrice;
        status; // Use Integer
        remark;
   }
   返回code
删除订单
    /shop/order/{id}
    method:delete
    params:id
    返回code
查询当日数据
    /shop/data
    method:get
    params:null
    返回businessdata
### 菜品管理
添加菜品
    /shop/dish/save
    method:post
    params:{ 
       pname   string
       price   Integer
       stock   Integer 
    }
    返回code
查找菜品
    /shop/dish/info/{id}
    method:get
    params:id
    返回dish
删除菜品
    /shop/dish/delete/{id}
    method:delete
    params:id
    返回code
修改菜品
    /shop/dish/update
    method:put
    params:{ 
       pname   string
       price   Integer
       stock   Integer 
    }
    返回code
菜品分页
    /shop/dish/page
    method:get
    params:{ 
        page   Integer //显示第几页
        size   Integer //每页显示多少条
        cateId  Integer //菜品分类
        stasus  Integer //排序方式
    }
    返回
    totle      总记录数
    list<product>  菜品集合
商家分类
    /shop/shop/page
    method:get
    params:{ 
        page   Integer //显示第几页
        size   Integer //每页显示多少条
        cateId  Integer //商家分类
        stasus  Integer //排序方式
    }
    返回
    totle      总记录数
    list<shop>  商家集合



