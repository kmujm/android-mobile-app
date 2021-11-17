# mobileProgramming
모바일프로그래밍 개인프로젝트

# 구현 기능
1. 첫번째 화면(RelativeLayout 사용)
  - 로그인 페이지
  - 이미 등록된 회원인 경우 로그인 가능(로그인 성공 Toast 생성), 그렇지 않은 경우 로그인 에러(로그인 실패 + error원인이 적힌 Toast 생성)
  - 아이디나 비밀번호 칸에 아무 내용도 입력되지 않았을 경우 해당 textedit칸에 에러를 띄움
  - 아이디를 이메일 형식이 아니게 입력한 경우 해당 textedit칸에 에러를 띄움
  - 로그인 버튼을 클릭하면 로그인이 완료될때까지 로그인중이라는 progressdialog 생성
  - 페이지 우측 상단 액자 모양 imagebutton 클릭시 세번째 페이지인 상품 리스트 페이지로 이동
  - 로그인 성공시에도 세번째 페이지인 상품 리스트 페이지로 이동
  - 회원가입 버튼 클릭시 두번째 화면인 회원가입 페이지로 이동
  
2. 두번째 화면(LinearLayout 사용)
  - 회원가입 페이지
  - 아이디, 비밀번호, 이름, 전화번호, 주소 editview로 생성
  - 페이지 하단의 회원가입 버튼 클릭시 아이디 중복검사, 비밀번호 규칙 체크, 전화번호 형식 유효성 검사
  - 아이디 중복이 발생한 경우 error message가 담긴 Toast 생성
  - 비밀번호, 전화번호 형식 오류시 해당 textedit칸에 error를 표시하고 focus
  - 개인 정보 사용 동의 관련 약관 textview
  - 동의 여부를 묻는 radiobutton
  - 회원가입 버튼 클릭시 회원가입에 필요한 모든 조건을 만족하면 회원가입 성공, 첫번째 페이지인 로그인 페이지로 이동
  - 회원정보는 firebase authentication(email과 password), firestore(email, name, phone, address)
  
3. 세번째 화면(ConstraintLayout 사용)
  - 상품명과 상품이미지를 리스트로 보여줌(5개 이상 기본으로 들어가있음-realtime database, 상품 하나하나 CustomAdapter로 생성)
  - 각 상품마다 삭제 버튼 존재, 해당 상품을 삭제함, realtime db에 연동되어 삭제됨
  - 하단의 +버튼 클릭시 상품이름과 상품 이미지 url을 입력한 후(AddActivity) 저장을 누르면 db에 업로드되어 리스트에 추가됨
  - \+ 버튼 위에 있는 사람모양의 버튼을 누르면 로그아웃 상태인 경우 로그아웃 상태임을 알려주고 회원가입 또는 로그인 페이지로 이동 가능
  - 그렇지 않은 경우, 즉 로그인 상태인 경우 현재 로그인한 유저의 이메일, 이름, 번호, 주소정보를 보여주고 로그아웃 버튼을 누르면 로그아웃됨(InfoActivity)


# 실행 환경
  - Pixel 2 API 30, Android 11.0(R)(Google Play), CPU/ABI : x86, Resolution : 1080 x 1920: 420dpi
  - 데모 시연 영상 : https://youtu.be/XSYwPVZ_fuw
  - ※아이디는 이메일 형식만 가능합니다
  - ※모든 데이터베이스는 파이어베이스와 연결(회원가입 및 회원 정보 : firestore + authentication, 상품 리스트 : realtime database)
