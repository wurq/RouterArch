package com.wurq.routerarch.router;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.wurq.routerarch.AbsApplication.AbsAction;
import com.wurq.routerarch.AbsApplication.AbsActionResult;
import com.wurq.routerarch.AbsApplication.AbsProvider;
import com.wurq.routerarch.AbsApplication.AbsRouterApplication;
import com.wurq.routerarch.AbsApplication.ErrorAction;
import com.wurq.routerarch.utils.ProcessUtil;

import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wurongqiu on 2017/9/18.
 */

public class LocalRouter {
    private static final String TAG = "LocalRouter";
    private String mProcessName = ProcessUtil.UNKNOWN_PROCESS_NAME;
    private static LocalRouter sInstance = null;
    private HashMap<String, AbsProvider> mProviders = null;
    private AbsRouterApplication mApplication;
//    private IWideRouterAIDL mWideRouterAIDL;
    private static ExecutorService threadPool = null;
//    private ServiceConnection mServiceConnection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            mWideRouterAIDL = IWideRouterAIDL.Stub.asInterface(service);
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            mWideRouterAIDL = null;
//        }
//    };

    private LocalRouter(AbsRouterApplication context) {
        mApplication = context;
        mProcessName = ProcessUtil.getProcessName(context, ProcessUtil.getMyProcessId());
        mProviders = new HashMap<>();
//        if (mApplication.needMultipleProcess() && !WideRouter.PROCESS_NAME.equals(mProcessName)) {
//            connectWideRouter();
//        }
    }

    public static synchronized LocalRouter getInstance(@NonNull AbsRouterApplication context) {
        if (sInstance == null) {
            sInstance = new LocalRouter(context);
        }
        return sInstance;
    }

    private static synchronized ExecutorService getThreadPool() {
        if (null == threadPool) {
            threadPool = Executors.newCachedThreadPool();
        }
        return threadPool;
    }

//    public void connectWideRouter() {
//        Intent binderIntent = new Intent(mApplication, WideRouterConnectService.class);
//        binderIntent.putExtra("domain", mProcessName);
//        mApplication.bindService(binderIntent, mServiceConnection, BIND_AUTO_CREATE);
//    }
//
//    public void disconnectWideRouter() {
//        if (null == mServiceConnection) {
//            return;
//        }
//        mApplication.unbindService(mServiceConnection);
//        mWideRouterAIDL = null;
//    }
//
//    public void registerProvider(String providerName, AbsProvider provider) {
//        mProviders.put(providerName, provider);
//    }
//
//
//    public boolean checkWideRouterConnection() {
//        boolean result = false;
//        if (mWideRouterAIDL != null) {
//            result = true;
//        }
//        return result;
//    }
//
//    boolean answerWiderAsync(@NonNull RouterRequest routerRequest) {
//        if (mProcessName.equals(routerRequest.getDomain()) && checkWideRouterConnection()) {
//            return findRequestAction(routerRequest).isAsync(mApplication, routerRequest.getData());
//        } else {
//            return true;
//        }
//    }

  /*  public RouterResponse route(Context context, @NonNull RouterRequest routerRequest) throws Exception {
        Log.d(TAG, "Process:" + mProcessName + "\nLocal route start: " + System.currentTimeMillis());
        RouterResponse routerResponse = new RouterResponse();
        // Local request
        if (mProcessName.equals(routerRequest.getDomain())) {
            HashMap<String, String> params = new HashMap<>();
            Object attachment = routerRequest.getAndClearObject();
            params.putAll(routerRequest.getData());
            Log.d(TAG, "Process:" + mProcessName + "\nLocal find action start: " + System.currentTimeMillis());
            AbsAction targetAction = findRequestAction(routerRequest);
            routerRequest.isIdle.set(true);
            Log.d(TAG, "Process:" + mProcessName + "\nLocal find action end: " + System.currentTimeMillis());
            routerResponse.mIsAsync = attachment == null ? targetAction.isAsync(context, params) : targetAction.isAsync(context, params, attachment);
            // Sync result, return the result immediately.
            if (!routerResponse.mIsAsync) {
                AbsActionResult result = attachment == null ? targetAction.invoke(context, params) : targetAction.invoke(context, params, attachment);
                routerResponse.mResultString = result.toString();
                routerResponse.mObject = result.getObject();
                Log.d(TAG, "Process:" + mProcessName + "\nLocal sync end: " + System.currentTimeMillis());
            }
            // Async result, use the thread pool to execute the task.
            else {
                LocalTask task = new LocalTask(routerResponse, params,attachment, context, targetAction);
                routerResponse.mAsyncResponse = getThreadPool().submit(task);
            }
        } else if (!mApplication.needMultipleProcess()) {
            throw new Exception("Please make sure the returned value of needMultipleProcess in MaApplication is true, so that you can invoke other process action.");
        }
        // IPC request
        else {
            String domain = routerRequest.getDomain();
            String routerRequestString = routerRequest.toString();
            routerRequest.isIdle.set(true);
            if (checkWideRouterConnection()) {
                Logger.d(TAG, "Process:" + mProcessName + "\nWide async check start: " + System.currentTimeMillis());
                //If you don't need wide async check, use "routerResponse.mIsAsync = false;" replace the next line to improve performance.
                routerResponse.mIsAsync = mWideRouterAIDL.checkResponseAsync(domain, routerRequestString);
                Logger.d(TAG, "Process:" + mProcessName + "\nWide async check end: " + System.currentTimeMillis());
            }
            // Has not connected with the wide router.
            else {
                routerResponse.mIsAsync = true;
                ConnectWideTask task = new ConnectWideTask(routerResponse, domain, routerRequestString);
                routerResponse.mAsyncResponse = getThreadPool().submit(task);
                return routerResponse;
            }
            if (!routerResponse.mIsAsync) {
                routerResponse.mResultString = mWideRouterAIDL.route(domain, routerRequestString);
                Logger.d(TAG, "Process:" + mProcessName + "\nWide sync end: " + System.currentTimeMillis());
            }
            // Async result, use the thread pool to execute the task.
            else {
                WideTask task = new WideTask(domain, routerRequestString);
                routerResponse.mAsyncResponse = getThreadPool().submit(task);
            }
        }
        return routerResponse;
    }*/

//    public boolean stopSelf(Class<? extends LocalRouterConnectService> clazz) {
//        if (checkWideRouterConnection()) {
//            try {
//                return mWideRouterAIDL.stopRouter(mProcessName);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//                return false;
//            }
//        } else {
//            mApplication.stopService(new Intent(mApplication, clazz));
//            return true;
//        }
//    }

//    public void stopWideRouter() {
//        if (checkWideRouterConnection()) {
//            try {
//                mWideRouterAIDL.stopRouter(WideRouter.PROCESS_NAME);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        } else {
//            Log.e(TAG, "This local router hasn't connected the wide router.");
//        }
//    }

    private AbsAction findRequestAction(RouterRequest routerRequest) {
        AbsProvider targetProvider = mProviders.get(routerRequest.getProvider());
        ErrorAction defaultNotFoundAction = new ErrorAction(false, AbsActionResult.CODE_NOT_FOUND, "Not found the action.");
        if (null == targetProvider) {
            return defaultNotFoundAction;
        } else {
            AbsAction targetAction = targetProvider.findAction(routerRequest.getAction());
            if (null == targetAction) {
                return defaultNotFoundAction;
            } else {
                return targetAction;
            }
        }
    }

    private class LocalTask implements Callable<String> {
        private RouterResponse mResponse;
        private HashMap<String, String> mRequestData;
        private Context mContext;
        private AbsAction mAction;
        private Object mObject;
        public LocalTask(RouterResponse routerResponse,
                         HashMap<String, String> requestData,
                         Object object, Context context,
                         AbsAction absAction) {
            this.mContext = context;
            this.mResponse = routerResponse;
            this.mRequestData = requestData;
            this.mAction = absAction;
            this.mObject = object;
        }

        @Override
        public String call() throws Exception {
            AbsActionResult result = mObject == null ? mAction.invoke(mContext, mRequestData) : mAction.invoke(mContext, mRequestData, mObject);
            mResponse.mObject = result.getObject();
            Log.d(TAG, "Process:" + mProcessName + "\nLocal async end: " + System.currentTimeMillis());
            return result.toString();
        }
    }

//    private class WideTask implements Callable<String> {
//
//        private String mDomain;
//        private String mRequestString;
//
//        public WideTask(String domain, String requestString) {
//            this.mDomain = domain;
//            this.mRequestString = requestString;
//        }
//
//        @Override
//        public String call() throws Exception {
//            Log.d(TAG, "Process:" + mProcessName + "\nWide async start: " + System.currentTimeMillis());
//            String result = mWideRouterAIDL.route(mDomain, mRequestString);
//            Log.d(TAG, "Process:" + mProcessName + "\nWide async end: " + System.currentTimeMillis());
//            return result;
//        }
//    }

//    private class ConnectWideTask implements Callable<String> {
//        private RouterResponse mResponse;
//        private String mDomain;
//        private String mRequestString;
//
//        public ConnectWideTask(RouterResponse routerResponse, String domain, String requestString) {
//            this.mResponse = routerResponse;
//            this.mDomain = domain;
//            this.mRequestString = requestString;
//        }
//
//        @Override
//        public String call() throws Exception {
//            Logger.d(TAG, "Process:" + mProcessName + "\nBind wide router start: " + System.currentTimeMillis());
//            connectWideRouter();
//            int time = 0;
//            while (true) {
//                if (null == mWideRouterAIDL) {
//                    try {
//                        Thread.sleep(50);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    time++;
//                } else {
//                    break;
//                }
//                if (time >= 600) {
//                    ErrorAction defaultNotFoundAction = new ErrorAction(true, MaActionResult.CODE_CANNOT_BIND_WIDE, "Bind wide router time out. Can not bind wide router.");
//                    MaActionResult result = defaultNotFoundAction.invoke(mApplication, new HashMap<String, String>());
//                    mResponse.mResultString = result.toString();
//                    return result.toString();
//                }
//            }
//            Logger.d(TAG, "Process:" + mProcessName + "\nBind wide router end: " + System.currentTimeMillis());
//            String result = mWideRouterAIDL.route(mDomain, mRequestString);
//            Logger.d(TAG, "Process:" + mProcessName + "\nWide async end: " + System.currentTimeMillis());
//            return result;
//        }
//    }
}
