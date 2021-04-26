# ServicePractice
안드로이드 4대 컴포넌트 중 하나인 서비스 연습

# Service
안드로이드 4대 컴포넌트 중 하나로, 백그라운드 작업 시 사용
- UI를 갖지 않는다
- 이를 실행시킨 주체와 독립적인 생명주기를 갖는다
## 백그라운드 작업
사실 서비스는 메인스레드에서 시작됨. 따라서 따로 스레드 작업을 해줘야 함
따라서, 호출한다고 바로 실행하는게 아니라 Looper의 MessageQueue에 Message가 들어가 메인스레드를 쓸 수 있는 시점에 시작함
## 생명주기
서비스는 크게
- Unbound Service: 서비스가 startService()로 생성된 경우
- Bound Service: 서비스가 bindService()로 생성된 경우 (startService()로 생성된 서비스에 바인딩할 수도 있음!!)

로 나뉨

![service_lifecycle](https://user-images.githubusercontent.com/31833972/116084807-1ed01700-a6d9-11eb-9171-bffc3f88c689.png)

### 전체 수명
onCreate() -> onDestroy() (모든 서비스에 대해 호출됨. 서비스가 startService()로 생성되었든 bindService()로 생성되었든!!)
onCreate(): 초기 설정
onDestroy(): 남은 리소스를 모두 릴리스 
### 활성수명
onStartCommand() 또는 onBind()에 대한 호출부터 시작
위의 각 메서드는 Intent를 받아 startService() 또는 bindService()에 전달
### 서비스 중단
#### Unbound Service
서비스자체에서 stopSelf()를 호출하거나 외부에서 stopService()를 호출해야 함
(단, stop에 대한 서비스콜백 메서드는 없고 수신되는 콜백은 onDestroy가 유일함)
#### Bound Service
bind된 모든 클라이언트에서 unbindService() 호출 시 시스템이 해당 서비스를 소멸
-> 따라서, 서비스가 스스로 중단하지 않아도 됨

+) startService()로 생성되었다가 바인드된 케이스는?
모든 클라이언트가 바인딩을 해제할 때까지 stopService(), stopSelf()가 서비스를 중단시키지 않음

TODO) https://developer.android.com/guide/components/services?hl=ko#CreatingBoundService
