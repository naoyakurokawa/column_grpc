DROP TABLE IF EXISTS articles;
CREATE TABLE articles
(
  id INT AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  detail TEXT NOT NULL,
  created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated DATETIME NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO articles (
  title,
  detail,
  created,
  updated
) VALUES (
  "マイクロサービスとは",
  "難しいです",
  "2022-03-22 00:00:00.000000",
  "2022-03-22 00:00:00.000000"
),
(
  "gRPCとは",
  "難しいです",
  "2022-03-22 00:00:00.000000",
  "2022-03-22 00:00:00.000000"
);
