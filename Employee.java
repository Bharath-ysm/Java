import java.util.*;
import java.util.stream.*;

class Employee 
{
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public static void main(String[] args) 
	{
        // Create a list of employees
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee("bharath", 22));
        employees.add(new Employee("yashu", 21));
        // Sort employees by age using Stream API
        List<Employee> sortedEmployees = employees.stream()
                .sorted(Comparator.comparingInt(Employee::getAge)).collect(Collectors.toList());

        // Print sorted employees
        sortedEmployees.forEach(employee -> System.out.println("Name: " + employee.getName() + ", Age: " + employee.getAge()));
    }
}
