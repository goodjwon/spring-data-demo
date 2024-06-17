## Srping Data REST 
> chat gpt를 활용하여 코드를 자동 생성하는 스크립트를 작성해 본다.

### 1. 요구사항 스크립트.
spring Data REST 에 대해서 설명해줘 간단한 crud 가능한 예제를 만들어줘.
- 예제는 주문도메인 관련
- table 2개 이상일때를 가정해줬으면 해 .
- 매우중요하게  java 17에서 javax 에서 jakarta 로 변경된 것이 많으니까 꼭 확인 해서 알려주고 (validation, persistence 등...)
- Spring Data REST 스팩을 이용해서만 만들어줘,
- entity와 dto를 구분했으면 하고
- 사용자 요청 및 응담은 dto를 통해서 했으면 해.
- gradle을 사용할꺼야.
- 패키지 구조도 표시해주고,
- lombok을 사용하고
- application.yml을 쓸꺼야.
- dto에 벨리데이션 설정을 해서 특정 값이 없다거나 잘못된 숫자나 문자가 들어 오면 예외 처리도 하고 싶어.
- 비동기로 요청 처리도 하고 싶고
- crud에 대한 슬라이스 테스트 케이스를 만들고
- service에 대한 슬라이스 케이스를 만들고
- 벨리데이션 대한 슬라이스 테스트 케이스를 만들고
- api에 대한 mock 테스트도 하고싶어.
- 각 서비스를 호출하는 url 을 표시 해주고
- curl 로 실행해 볼 수 있는 커맨드도 제출해줘.
- 스프링부트는 안정성이 확인 된 것 중 최신것으로 사용해줘.
- spring 예제를 참고 할때도 꼭 runtime 을 확인 해서 코드를 제공해줘
- 패키지 명은 com.example.ordersystem 을 기본으로 해줘
- spring data jpa로 해결 안되는 요청 사항에 대해서는 spring data jpa 예제를 만드는 거니까 안되는 이유를 설명하고 spring data jpa 스팩에서 되는 걸로 조정해서 코드를 만들어줘
- spring boot 는 3.1 대 버전을 사용해줘
- Order, OrderItem 으로 도메인을 만들어줘 테이블명은 orders, order_items 로 만들어줘
- convertToDto, convertToEntity 통해서 dto와 entity 사이를 변환했으면 좋겠어.
- 엔티티명은 Order, OrderItem 태이블명은 orders, order_item