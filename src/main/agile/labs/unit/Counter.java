package agile.labs.unit;

public class Counter {
    int value ;
    int limit;

    public Counter(int limit) {
        this.limit = limit;
        value = 0 ;
    }

    public int getValue() {
        return value;
    }

    public int increment() {
        if ( ++value > limit) {  
            value = 0;
        }
        return value ;
    }
    
    public int step(int amount) {
        int diff = (value + amount) % limit ;
        if ( diff > 0) {  
            value = diff;
        } else {
            value += amount; 
        }
        return value ;
    }
}