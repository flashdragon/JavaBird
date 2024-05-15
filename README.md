## 설명
## 프로젝트 아키텍쳐
![Untitled](https://github.com/flashdragon/JavaBird/assets/35221784/ef1abdf6-0556-45fe-b831-c30b9c2edb83)


### Java Spring으로 sns를 간단하게 만들어 보았습니다.

#### #Java #Spring #MyBatis


# 정적인 class 의존관계
![image](https://github.com/flashdragon/JavaBird/assets/35221784/767649e3-a7b3-4792-ae37-3a00ebac14f9)

# API
|method|url|설명|
|----|----|----|
|get|/api|홈화면으로 많은 post들의 정보를 가져온다.|
|post|/api/login|로그인 사용자 정보를 준다.|
|post|/api/logout|로그인 세션 삭제|
|post|/api/post|포스터 생성|
|delete|/api/{postId}|포스트 삭제|
|post|/api/signup|회원 가입|
|post|/api/follow/{memberId}|팔로우|
|post|/api/unfollow/{memberId}|언팔로우|



# 그림
## 1. 홈 화면
![image](https://github.com/flashdragon/JavaBird/assets/35221784/44b17db8-52c8-48ff-a171-e0e56cb9605d)
## 2. 로그인 후
![image](https://github.com/flashdragon/JavaBird/assets/35221784/a2bef24e-c272-4183-a3f0-f3f18d5678f6)

