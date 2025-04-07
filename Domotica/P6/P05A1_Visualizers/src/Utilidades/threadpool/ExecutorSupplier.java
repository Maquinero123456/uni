package Utilidades.threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

public interface ExecutorSupplier {
	ThreadPoolExecutor forCommListen();

    Executor forBackgroundTasks();
}
