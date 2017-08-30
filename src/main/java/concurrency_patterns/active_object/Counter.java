package concurrency_patterns.active_object;

	//This class lists all the interfaces that are exposed to outer world as service.which is invoked but executed later due to being ActiveObject.
	public interface Counter {

	    /**
	     * Gets the current value
	     * @return the current value
	     */
	    long get();

	    /**
	     * Atomically increments the value by one
	     * @return the incremented value
	     */
	    long incrementAndGet();

	    /**
	     * Atomically increments the value by one
	     * @return the current (non-incremented) value
	     */
	    long getAndIncrement();

	    /**
	     * Atomically decrements the value by one
	     * @return the decremented value
	     */
	    long decrementAndGet();

	    /**
	     * Atomically decrements the value by one
	     * @return the current (non-decremented) value
	     */
	long getAndDecrement();
	}
