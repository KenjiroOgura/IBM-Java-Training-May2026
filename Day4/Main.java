import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    static void main() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", "IT", 55000));
        employees.add(new Employee("Bob", "Finance", 60000));
        employees.add(new Employee("Alice", "HR", 52000)); // duplicate name
        employees.add(new Employee("Ken", "IT", 60000));
        employees.add(new Employee("Maria", "HR", 50000));
        employees.add(new Employee("John", "Finance", 70000));
        employees.add(new Employee("Ken", "Finance", 65000)); // duplicate name
        employees.add(new Employee("Lara", "IT", 62000));
        employees.add(new Employee("Sam", "HR", 48000));
        employees.add(new Employee("Bob", "IT", 59000)); // duplicate name
        employees.add(new Employee("Dennis", "BPO", 38000));
        
        Set <String> names = new HashSet<>();
       
        for (Employee e : employees) {
            if(!names.contains(e.getName())){
                names.add(e.getName());
                System.out.println(e);
            }
            
        }
    
        System.out.println(names);

        Map <String, List<Employee>> employeeMapByDept = new HashMap<>();
        for (Employee e : employees) {
            employeeMapByDept.putIfAbsent(e.getDepartment(), new ArrayList<>());
            employeeMapByDept.get(e.getDepartment()).add(e);            
        }
        
      
        for (Map.Entry<String, List<Employee>> entry : employeeMapByDept.entrySet()) {
            System.out.println(entry.getKey()+":");
            
            for (Employee emp : entry.getValue()) {
                System.out.println(" - " + emp.getName()+"("+emp.getSalary()+")");
            }
}

    System.out.println("\n=== Highest paid per Department ===");
    for (String deptName: employeeMapByDept.keySet()) {
        List<Employee> empList = employeeMapByDept.get(deptName);
        Employee empMax = empList.get(0);
        for (Employee emp : empList) {
            if (emp.getSalary() > empMax.getSalary()) {
                empMax = emp;
            }
        }
        System.out.println(empMax.toString());
    }

  
    
    System.out.println("\n==== Highest Salaries of all Employees ===");
    Comparator myComparator = new SortbySalary();
    Collections.sort(employees,myComparator);
    for (Employee e : employees) {
        System.out.println(e);
    }

    HashSet<Double> uniqSalary = new HashSet<>();
    for (Employee e : employees) {
        uniqSalary.add(e.getSalary());
    }

    TreeSet <Double> ts = new TreeSet<>();
    ts.addAll(uniqSalary);
    System.out.println("\n=== Unique Salaries (Sorted) ===");
    for (double n : ts) {
      System.out.println(n);
    }
    
    
    }
static class SortbySalary implements Comparator {
  public int compare(Object obj1, Object obj2) {
    Employee a = (Employee) obj1;
    Employee b = (Employee) obj2;
    
    // Compare the objects
    if (a.getSalary() < b.getSalary()) return 1; 
    if (a.getSalary() > b.getSalary()) return -1;  
    return 0; 
  }
}
}