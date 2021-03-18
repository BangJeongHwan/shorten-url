프로젝트 jar 파일 생성 및 리눅스로 옮기기
1. jar 파일 생성
    - Gradle -> Tasks -> build -> bootJar 더블 클릭
2. jar 파일 생성 확인
    - /shorten-url/build/libs/shorten-url-0.0.1-SNAPSHOT.jar
3. jar 파일 리눅스로 옮기기
    - ftp, sftp 등으로 리눅스 서버에 옮기기


jdk 설치 하기
1. java 설치 여부 확인
    - "# java -version"
2. 설치 가능한 JDK 버전 확인
    - "# yum list java*jdk-devel"
3. 없을 경우 JDK 설치
    - "# yum install java-1.8.0-openjdk-devel.x86_64"
4. JDK 정상적으로 설치되었는지 확인
    - "# java -version"
    

jdk 로 jar 실행시키기
1. jar 파일 권한 부여
    - chmod u+x /설치 디렉토리/shorten-url-0.0.1-SNAPSHOT.jar (유저 권한)
    - chmod g+x /설치 디렉토리/shorten-url-0.0.1-SNAPSHOT.jar (그룹 권한)
2. jar 실행하기
    - java -jar shorten-url-0.0.1-SNAPSHOT.jar
3. "Caused by: java.net.SocketException: 허가 거부" 이슈 해결
    - 80 포트는 관리자 권한이 필요
    - sudo java -jar shorten-url-0.0.1-SNAPSHOT.jar


관리자 웹 접속
1. http://설치서버IP/shorten-url 로 접속
