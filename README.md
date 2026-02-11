# save_budget_management
📑 프로젝트: 동아리 회비 및 예산 관리 시스템 (Save-Budget)
1. 프로젝트 개요
목적: 동아리 운영의 투명성을 위해 회비 납부 현황 및 예산 지출 내역을 실시간으로 관리하고 공유하는 웹 서비스입니다.

핵심 기능:

회원 관리: 회원 등록, 수정, 삭제 및 권한(ADMIN/USER)별 데이터 열람 제한.

회비 관리: 회원별 회비 납부 내역 등록 및 천 단위 콤마(,)를 적용한 가독성 높은 리스트 제공.

검색 시스템: 이름, 학과, 날짜 등 다양한 조건으로 데이터 필터링.

보안 기능: 관리자(ADMIN) 외 민감한 개인정보(학번, 전화번호 등) 노출 차단.

2. Tech Stack (Fact Check)
Backend: Java 17, Spring Boot, Spring Data JPA

Database: MySQL 8.0

Frontend: Thymeleaf, Bootstrap 4

Infrastructure: AWS EC2 (Ubuntu 22.04 LTS), GitHub

3. 인프라 구축 및 트러블슈팅 (핵심 성과)

서버 자원 최적화 (Memory Management):

문제: AWS Free Tier(RAM 1GB) 환경에서 Gradle 빌드 시 메모리 부족으로 인한 서버 중단 현상 발생.

해결: EBS 용량 중 2GB를 **스왑 메모리(Swap Memory)**로 할당하여 가용 메모리를 확보함으로써 안정적인 빌드 환경 구축.

CI/CD 배포 파이프라인 간소화:

기존: FTP(FileZilla)를 통한 수동 jar 업로드 방식.

개선: GitHub와 서버를 연동하여 git pull 후 서버 내 즉시 빌드 및 실행 루틴 구축. 배포 시간을 단축하고 버전 관리 효율성을 극대화함.

프로세스 관리: pkill -f java 명령어를 활용한 기존 프로세스 자원 회수 및 재배포 자동화 기초 마련.

4. DB & 보안 설정
외부 접속 통제: MySQL bind-address 설정 및 AWS 보안 그룹을 활용하여 특정 IP에서만 DB 접근이 가능하도록 화이트리스트 기반 보안 적용.

데이터 정합성: JPA를 활용한 객체 지향적 설계로 회비 데이터와 회원 데이터 간의 연관 관계 매핑.
