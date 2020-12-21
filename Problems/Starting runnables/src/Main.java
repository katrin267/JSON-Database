class Starter {

    public static void startRunnables(Runnable[] runnables) {
        for (Runnable runnable : runnables) {
            Thread thread = new Thread(runnable);
            thread.start();
        }
//        Arrays.stream(runnables).map(Thread::new).forEach(Thread::start);
//        Stream.of(runnables).map(Thread::new).forEach(Thread::start);
    }
}