public class Employee {
    private String name;
    private String department;
    private double salary;

    public Employee(){}
    public Employee(String name,String department, double salary){
        this.name=name;
        this.department= department;
        this.salary= salary;
    }

    public String getName(){
        return  name;
    }
    public String getDepartment(){
        return department;
    }

    public double getSalary(){
        return salary;
    }

    private void setName(String name){
        this.name=name;
    }
    public void setDepartment(String department) {
        this.department = department;
    }        
    
    private void setSalary(double salary){
        this.salary = salary;
    }
    public void setDetails(String name,String department,double salary){
        setName(name);
        setDepartment(department);
        setSalary(salary);
    }
    @Override
    public String toString(){
        return getName()+"|"+getDepartment()+"| $"+getSalary();
    }
}
