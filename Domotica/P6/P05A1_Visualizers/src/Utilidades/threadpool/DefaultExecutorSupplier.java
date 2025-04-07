package Utilidades.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DefaultExecutorSupplier implements ExecutorSupplier {
	
	private static final int NUMBER_OF_CORES  = Runtime.getRuntime().availableProcessors();
	
	/*
     * thread pool executor for background tasks
     */
    private final ThreadPoolExecutor mforCommListen;
    private final Executor backgroundExecutor;
    
    /*
     * an instance of DefaultExecutorSupplier
     */
    private static DefaultExecutorSupplier sInstance;

    /*
     * returns the instance of DefaultExecutorSupplier
     */
    public synchronized static DefaultExecutorSupplier getInstance() {
        if (sInstance == null) {
            synchronized (DefaultExecutorSupplier.class) {
                sInstance = new DefaultExecutorSupplier();
            }
        }

        return sInstance;
    }
	
	DefaultExecutorSupplier(){
		//Get the ThreadFactory implementation to use
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
		
        mforCommListen = new ThreadPoolExecutor(NUMBER_OF_CORES*2, NUMBER_OF_CORES*2, 2, 
				                    TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10),threadFactory);	
        
        backgroundExecutor = Executors.newSingleThreadExecutor();
	}

	@Override
	public Executor forBackgroundTasks() {
		//Auto-generated method stub
		return backgroundExecutor;
	}

	@Override
	public ThreadPoolExecutor forCommListen() {
		return mforCommListen;
	}

}
