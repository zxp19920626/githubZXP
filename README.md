# githubZXP

## 框架图

![框架图](https://github.com/zxp19920626/githubZXP/blob/master/app/src/main/res/drawable/fc.png)

## 基于Kotlin的Jetpack组件

1、DataBinding+LiveData+ViewModel框架为基础，整合Okhttp+RxJava+Retrofit等流行模块

2、加上原生控件自定义的BindingAdapter，让事件与数据源完美绑定的一款容易上瘾的实用性MVVM快速开发框架。从此告别findViewById()，告别setText()，告别setOnClickListener()

#### 框架特点
1、快速开发：
  专心写项目的业务逻辑，不用过多关心网络请求、View的生命周期等问题

2、层次分明：
  ViewModel层负责将请求到的数据做业务逻辑处理，最后交给View层去展示，与View一一对应；View层只负责界面绘制刷新，不处理业务逻辑

3、流行框架：
  retrofit+okhttp+rxJava负责网络请求；gson负责解析json数据

4、基类封装：
  专门针对MVVM模式打造的BaseActivity、BaseFragment、BaseAdapter、BaseViewModel
  


## 代码部分
#### 接口请求：
```
/**
 * @version ：声明接口
 * @description ：
 * @data ：2022/8/27
 * @auther ：Zengxiaoping
 */
interface ApiService {

    /**
     * 查询个人信息
     */
    @GET("user/{username}")
    fun getUserInfoBean(@Path("username") username: String): Observable<UserInfoBean>

    /**
     * 查询某人的关注列表
     */
    @GET("users/{username}/following")
    fun getFollowingList(@Path("username") username: String): Observable<List<FollowingBean>>
}
```

#### viewmodel：
```
/**
 *  @version ：
 *  @description ：某人的关注页面
 *  @data ：2022/8/27
 *  @auther ：Zengxiaoping
 */
class FollowViewModel : BaseViewModel() {

    //观测数据
    var mFollowListLiveData = MutableLiveData<List<FollowingBean>>()

    /**
     * 获取某人关注用户列表信息
     */
    fun getUserInfoBean(userName: String, iFollowViewCallBack: IFollowViewCallBack) {
        val disposable =
            Api.mInstance.getFollowingList(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isNotEmpty()) {
                        mFollowListLiveData.value = it
                    } else {
                        iFollowViewCallBack.requestNoData()
                    }
                }, {
                    iFollowViewCallBack.requestError()
                })
        addDisposable(disposable)
    }
}
```

#### 定义好抽象基类Adapter，后续实现类adapter将会很简洁：
```
public abstract class BaseDBRVAdapter<Data, DB extends ViewDataBinding> extends RecyclerView.Adapter<BaseDBRVHolder> {
  ...
}
```

```
/**
 *  @version ：
 *  @description ：某人关注列表适配器
 *  @data ：2022/8/27
 *  @auther ：Zengxiaoping
 */
class FollowListAdapter : BaseDBRVAdapter<FollowingBean, ItemFollowListBinding>(
    R.layout.item_follow_list,
    BR.followingBean
)
```

#### 数据绑定到xml布局：

```
/**
 *  @version ：
 *  @description ：发现页面
 *  @data ：2022/8/27
 *  @auther ：Zengxiaoping
 */
class DiscoveryFragment : BaseFragment<FragmentTvCenterLayoutBinding>() {

    companion object {
        fun newInstance(): DiscoveryFragment = DiscoveryFragment()
    }

    override fun layoutId(): Int = R.layout.fragment_tv_center_layout

    override fun bindData() {
        mDataBinding.name = "发现"
    }
}
```
```
<layout xmlns:android="http://schemas.android.com/apk/res/android">
    <data>
        <variable
            name="name"
            type="String" />
    </data>
    <TextView
        android:id="@+id/tv_name"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center"
        android:text="@{name}"
        android:textSize="20sp" />
</layout>
```
