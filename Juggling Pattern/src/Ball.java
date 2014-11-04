import java.util.ArrayList;

public class Ball {

    public Ball(int land, int name) {
        _land = land;
        _name = name;
        _values = new ArrayList<Integer>();
    }

    public void set_land(int land) {
        _land = land;
    }

    public int get_land() {
        return _land;
    }

    public int get_name() {
        return _name;
    }

    public void add_value(int val) {
        if (!_values.contains(val)) {
            _values.add(val);
        }
    }

    public ArrayList<Integer> get_values() {
        return _values;
    }

    private int _land;
    private int _name;
    private ArrayList<Integer> _values;
}