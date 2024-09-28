class AnimalThread extends Thread {
    private String name;
    private int priority;
    private int distance = 0;

    public AnimalThread(String name, int priority) {
        this.name = name;
        this.priority = priority;
        this.setName(name);
        this.setPriority(priority);
    }

    @Override
    public void run() {
        while (distance < 100) { // Дистанция для завершения
            distance += (int) (Math.random() * 10); // Случайное расстояние от 0 до 9
            System.out.println(name + " пробежал " + distance + " метров.");
            try {
                Thread.sleep(500); // Задержка для имитации времени бега
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name + " финишировал!");
    }

    public int getDistance() {
        return distance;
    }
}

class RabbitAndTurtle {
    public static void main(String[] args) throws InterruptedException {
        AnimalThread rabbit = new AnimalThread("Кролик", Thread.MAX_PRIORITY);
        AnimalThread turtle = new AnimalThread("Черепаха", Thread.MIN_PRIORITY);

        rabbit.start();
        turtle.start();

        while (rabbit.isAlive() || turtle.isAlive()) {
            // Изменение приоритета, если одна из зверушек отстает
            if (turtle.getDistance() < rabbit.getDistance()) {
                turtle.setPriority(Thread.NORM_PRIORITY);
                rabbit.setPriority(Thread.MIN_PRIORITY);
            } else {
                rabbit.setPriority(Thread.NORM_PRIORITY);
                turtle.setPriority(Thread.MAX_PRIORITY);
            }

            Thread.sleep(1000); // Задержка для наблюдения за изменением приоритета
        }

        System.out.println("Соревнование завершено!");
    }
}