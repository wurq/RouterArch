package com.wurq.base.constant;

/**
 * Created by wurongqiu on 2017/6/19.
 */

public class CommonConst {

    /**
     * 前后台通讯常量
     */
    public static final String StartPlayer = "start_player";
    public static final String VIDEO_URL = "video_url";
    public static final String StartPlayerDone = "start_player_done";

    public static class NetWorkParams {

        public static final int NETWORK_ONLINE = 1;//线上
        public static final int NETWORK_DEV_03 = 2;//dev03
        public static final int NETWORK_DEV_SIT = 3;//SIT 环境
        public static final String DATA_CAN_WATCH = "DATA_CAN_WATCH";
        public static final String MOCK_URL = "http://rap.ikuko.com/mockjsdata/73/";
        public static final boolean isMock = false;
        private static final String DEV03_KAPI_URL = "http://10.0.0.142:8003/";
        private static final String SIT_KAPI_URL = "http://10.0.0.163:8001/";
        private static final String ONLINE_KAPI_URL = "http://kapi.kaike.la/";
        public static String video_url;
        public static int netWorkType = 1;//网络类型
        public static String kapi_url;
        static {
            switch (netWorkType) {
                case NETWORK_ONLINE:
                    kapi_url = ONLINE_KAPI_URL;
                    video_url = "http://stream.kaike.la/";
                    break;
                case NETWORK_DEV_03:
                    kapi_url = DEV03_KAPI_URL;
                    video_url = "http://stream.stable.ikuko.com/";
                    break;
                case NETWORK_DEV_SIT:
                    kapi_url = SIT_KAPI_URL;
                    video_url = "http://stream.kaike.la/";
                    break;
                default:
                    kapi_url = "";
                    video_url = "";
                    break;
            }
        }
    }

    public static class HttpCode {

        public static final int NETWORK_REQUEST_ERROR = -100;       //
        public static final int NETWORK_CACHE_PARSER_ERROR = -101;       //
        public static final int NETWORK_CACHE_NOT_SURPPORT = -102;       //

        public static final int NETWORK_RETURN_SUCCESS = 200;       //

        public static final int NO_NETWORK = -200;//无网络服务
        public static final int DATA_ERROR = -201;//数据错误
        public static final int DATA_LOST = -202;//数据缺失
        public static final int KAPI_ERROR = -203;//网关错误
        public static final int BUSINESS_ERROR = -300;//业务错误
        public static final int VIDEO_SERVER_ERROR = -300;//视频服务器异常

        //网关错误码
        public static final int NETWORK_REQUEST_UNKNOWN_ERROR = -1;       //未知错误
        public static final int NETWORK_ERROR_TOKEN_INVALID = 1002001;//token失效
        public static final int NETWORK_ERROR_NO_TOKEN = 1001010;//无token
    }

    public static class AppPlatform {
        public static final String MSGID = "msgId";
        public static final String PLATFORM = "platform";
        public static final String ANDROID = "Android";

        public static final String SOURCE = "source";
        public static final String REQUEST = "request";
//        public static final String APP_NAME          = "com.mistong.kklonline.kklonline";
//        public static final String EXTRA_BUNDLE      = "extra_bundle";
//        public static final String UMENTG_KEY        = "59546233a40fa36def001077";

        public static final int NUMBER_ONE = 1;

        public static final String IN_BUNDLE = "inBundle";
        public static final String GROUP_ID = "groupId";
        public static final String PRODUCT_ID = "productId";
        public static final String LIVE_ID = "liveId";
        public static final String CLASS_ID = "classId";
        public static final String COURSE_ID = "courseId";
        public static final String LESSON_ID = "lessonId";
        public static final String INTERACTION_ID = "interactionId";
        public static final String RESOURCE_ID = "resourceId";
    }

    public static class HttpContentType {
        public static final String CONTENT_TYPE_JSON = "application/json; charset=utf-8";
        public static final String CONTENT_TYPE_FILE = "multipart/form-data";
    }


    /**
     * 运行类别
     */
    public final class RunType {
        public static final int RUN_IN_FOREGROUND = 0;
        public static final int RUN_IN_BACKGROUND = 1;
    }


    /*
   * 进程名字
   */
    public final class AppConstants {
        public static final int TIME_OUT_NUM = 15;
    }

}
