// ILocalRouterAIDL.aidl
package com.wurq.routerarch.router;

// Declare any non-default types here with import statements

interface ILocalRouterAIDL {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

    boolean checkResponseAsync(String routerRequset);
    String route(String routerRequest);
    boolean stopWideRouter();
    void connectWideRouter();
}
