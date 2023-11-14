# LTalk

  평소 카카오톡과 디스코드를 이용하여 친구들과 소통하고 있습니다.
  이런 프로그램을 이용하다 자주 이용하다 보니 나와 친구들만의 프로그램을 만들고 이용하면 "좋을 것 같아"를 시작으로 프로젝트를 계획하게 되었습니다.
  디자인 적인 요소는 가장 많이 이용하는 카카오톡을 모티브로 삼았습니다.
  

                          <구현 목표>
  
    1. 실제 친구들과 이용할 수 있을 만큼의 기능을 구현하자 -가장 중요-
    2. 채팅과 음성채팅을 구현하자
    3. 프로젝트의 디자인 모티브는 카카오톡을 모티브로 삼아보자!





              <구현 기능의 목록과 우선순위 [내림 차순]>
    
    1. 채팅       - [TCP-IP 통신]
    2. 음성채팅   - [UDP 통신] -> [Multicast 통신] // 처음에는 UDP통신으로 생각하였지만 5명이서 게임을 하는 상황까지 고려하여 Multicast통신을 이용하기로 하였습니다.
    3. 로그인     - [OracleDB 이용]
    4. 친구등록   - [OracleDB 이용]
    
    
                    <이용 시스템>
           Language          Java 11.0.2
           GUI               JavaFX 18.0.2
           GUI Tool          SceneBuilder 2.0
           IDE               Eclipse 20210924-0641
           DataBase          OracleDB 21c 
           DataBase Tool     SQLDeveloper 22.2.0.173.2018
           


<로그인 화면>


![LTalk Login](https://github.com/LeeJaeHyung/LTalk/assets/69907023/0b08f482-9075-4aff-aef1-b29707b264be)


<친구목록>


![friend](https://github.com/LeeJaeHyung/LTalk/assets/69907023/c02a1085-c996-40ef-a4a4-f02af8baf08a)

<채팅목록>


![chatList](https://github.com/LeeJaeHyung/LTalk/assets/69907023/1b3cc47f-d088-4e44-9563-df13ee94d17f)

<채팅방>


![chat](https://github.com/LeeJaeHyung/LTalk/assets/69907023/54bba3c0-4c4f-4d2b-ba2a-04d1ad166da3)



[현제 구현 되어있는 기능]
  
  <클라이언트>
    로그인, 채팅, 음성채팅(구현되기만한 상태 추후 추가작업 필요)

  <서버>
    로그인 처리, 채팅전송 밑 클라이언트 간의 연결 처리


[구현중인 과정]

  <클라이언트>
    채팅내용 저장 및 처리,
    
  <서버>
    채팅에 대한 저장 및 처리,
  
