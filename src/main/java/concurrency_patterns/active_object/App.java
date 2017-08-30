package concurrency_patterns.active_object;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ThreadSafeCounter counter = new ThreadSafeCounter(100l);
        counter.decrementAndGet();
        counter.decrementAndGet();
        counter.decrementAndGet();
        
        counter.decrementAndGet();
        counter.decrementAndGet();
        counter.decrementAndGet();
        
        counter.decrementAndGet();
        counter.decrementAndGet();
        counter.decrementAndGet();
        counter.decrementAndGet();
    }
}
