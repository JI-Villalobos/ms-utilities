package org.jjv.instances;

public class TaskCompleteInstance {
    private static boolean clientProcessingCompleted;
    private static boolean providerProcessingCompleted;
    public static void initialStatus(){
        clientProcessingCompleted = false;
        providerProcessingCompleted = false;
    }

    public static void completeClientProcess(){
        clientProcessingCompleted = true;
    }
    public static void completeProviderProcess(){
        providerProcessingCompleted = true;
    }
    public static boolean getClientProcessStatus(){
        return clientProcessingCompleted;
    }

    public static boolean getProviderProcessStatus(){
        return providerProcessingCompleted;
    }

}
