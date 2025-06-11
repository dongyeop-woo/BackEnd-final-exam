-- 테스트용 사용자 데이터
INSERT INTO user (username, password, email, first_name, last_name) VALUES
('test', 'test123', 'test@example.com', '테스트', '사용자');

-- 베스트셀러 도서 데이터
INSERT INTO book (name, author, category, book_best, image_url) VALUES
('침묵의 봄', '레이첼 카슨', 'ENVIRONMENT', 'YES', 'https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788962632729.jpg'),
('코스모스', '칼 세이건', 'SCIENCE', 'YES', 'https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788983711892.jpg'),
('사피엔스', '유발 하라리', 'HISTORY', 'YES', 'https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788934972464.jpg'),
('데미안', '헤르만 헤세', 'NOVEL', 'YES', 'https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788937460449.jpg'),
('1984', '조지 오웰', 'NOVEL', 'YES', 'https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788937460777.jpg'),
('돈의 심리학', '모건 하우절', 'ECONOMY', 'YES', 'https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9791191056372.jpg'),
('아몬드', '손원평', 'NOVEL', 'YES', 'https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788936434267.jpg'),
('파과', '구병모', 'NOVEL', 'YES', 'https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9791162203620.jpg'),
('설민석의 조선왕조실록', '설민석', 'HISTORY', 'YES', 'https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788933870693.jpg'),
('해리포터와 마법사의 돌', 'J.K. 롤링', 'FANTASY', 'YES', 'https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9791193790403.jpg');

-- 일반 도서 데이터
INSERT INTO book (name, author, category, book_best, image_url) VALUES
('클린 코드', '로버트 마틴', 'TECHNOLOGY', 'NO', 'https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788966260959.jpg'),
('이것이 자바다', '신용권', 'TECHNOLOGY', 'NO', 'https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788968481475.jpg'),
('미드나잇 라이브러리', '매트 헤이그', 'NOVEL', 'NO', 'https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9791191056556.jpg'),
('부의 추월차선', '엠제이 드마코', 'ECONOMY', 'NO', 'https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9791187444725.jpg'),
('정의란 무엇인가', '마이클 샌델', 'PHILOSOPHY', 'NO', 'https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788937834790.jpg');

INSERT INTO book (name, author, category, book_best, image_url) VALUES
('달러구트 꿈 백화점', '이미예', 'FANTASY', 'YES', 'https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9791165341909.jpg'),
('공정하다는 착각', '마이클 샌델', 'PHILOSOPHY', 'YES', 'https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9791164136452.jpg'),
('어떻게 말해줘야 할까', '오은영', 'PARENTING', 'YES', 'https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788934986652.jpg'),
('작별인사', '김영하', 'NOVEL', 'YES', 'https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9791191114225.jpg'),
('불편한 편의점', '김호연', 'NOVEL', 'YES', 'https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9791161571188.jpg'); 