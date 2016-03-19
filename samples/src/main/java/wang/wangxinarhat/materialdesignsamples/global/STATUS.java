package wang.wangxinarhat.materialdesignsamples.global;

public class STATUS {
    /**
     * 加载几种状态
     */
    /**
     * 正在加载中
     */
    public static final int LOAD_LOADING = 0xa21;
    /**
     * 网络出错
     */
    public static final int LOAD_ERROR = 0xa22;
    /**
     * 数据为空
     */
    public static final int LOAD_EMPTY = 0xa23;
    /**
     * 加载成功
     */
    public static final int LOAD_SUCCESS = 0xa24;
    /**
     * 刷新数据成功
     */
    public static final int LOAD_RELOAD_SUCCESS = 0xa25;
    /**
     * 已经全部夹在完成
     */
    public static final int LOAD_ALL = 0xa26;
    /**
     * 加载完成,但是无数据
     */
    public static final int LOAD_COMPLETE = 0xa27;
}