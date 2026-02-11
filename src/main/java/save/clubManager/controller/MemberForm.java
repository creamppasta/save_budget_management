package save.clubManager.controller;

public class MemberForm {

    private Long id;
    private String name;
    private String studentId;
    private String department;
    private String phoneNumber;
    private String etc;

    // --- 롬복(@Getter, @Setter) 대신 직접 쓴 코드들 ---

    // 0. 아이디 (Name)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    // 1. 이름 (Name)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // 2. 학번 (StudentId)
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    // 3. 학과 (Department)
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    // 4. 전화번호 (PhoneNumber)
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // 5. 기타 (Etc)
    public String getEtc() {
        return etc;
    }
    public void setEtc(String etc) {
        this.etc = etc;
    }
}