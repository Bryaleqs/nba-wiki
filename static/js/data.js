// Historico completo de campeonatos NBA 1947-2026.
// Version estatica: estos datos simulan la base de datos (arreglo JS).
// La version JavaWeb obtiene lo mismo en vivo desde la base de datos via JDBC.

const CAMPEONATOS = [
  {
    anio: 2026, campeon: "Knicks", ciudad: "New York", abbr: "NYK", color: "#F58426", conferencia: "Este",
    finalista: "Spurs", resultado: "4-1", mvp: "Jalen Brunson",
    roster: ["Jalen Brunson", "OG Anunoby", "Karl-Anthony Towns", "Josh Hart"]
  },
  {
    anio: 2025, campeon: "Thunder", ciudad: "Oklahoma City", abbr: "OKC", color: "#007AC1", conferencia: "Oeste",
    finalista: "Pacers", resultado: "4-3", mvp: "Shai Gilgeous-Alexander",
    roster: ["Shai Gilgeous-Alexander", "Jalen Williams", "Chet Holmgren"]
  },
  {
    anio: 2024, campeon: "Celtics", ciudad: "Boston", abbr: "BOS", color: "#007A33", conferencia: "Este",
    finalista: "Mavericks", resultado: "4-1", mvp: "Jaylen Brown",
    roster: ["Jaylen Brown", "Jayson Tatum", "Kristaps Porzingis"]
  },
  {
    anio: 2023, campeon: "Nuggets", ciudad: "Denver", abbr: "DEN", color: "#0E2240", conferencia: "Oeste",
    finalista: "Heat", resultado: "4-1", mvp: "Nikola Jokic",
    roster: ["Nikola Jokic", "Jamal Murray", "Aaron Gordon"]
  },
  {
    anio: 2022, campeon: "Warriors", ciudad: "Golden State", abbr: "GSW", color: "#1D428A", conferencia: "Oeste",
    finalista: "Celtics", resultado: "4-2", mvp: "Stephen Curry",
    roster: ["Stephen Curry", "Klay Thompson", "Draymond Green", "Andrew Wiggins"]
  },
  {
    anio: 2021, campeon: "Bucks", ciudad: "Milwaukee", abbr: "MIL", color: "#00471B", conferencia: "Este",
    finalista: "Suns", resultado: "4-2", mvp: "Giannis Antetokounmpo",
    roster: ["Giannis Antetokounmpo", "Khris Middleton", "Jrue Holiday"]
  },
  {
    anio: 2020, campeon: "Lakers", ciudad: "Los Angeles", abbr: "LAL", color: "#552583", conferencia: "Oeste",
    finalista: "Heat", resultado: "4-2", mvp: "LeBron James",
    roster: ["LeBron James", "Anthony Davis", "Rajon Rondo"]
  },
  {
    anio: 2019, campeon: "Raptors", ciudad: "Toronto", abbr: "TOR", color: "#CE1141", conferencia: "Este",
    finalista: "Warriors", resultado: "4-2", mvp: "Kawhi Leonard",
    roster: ["Kawhi Leonard", "Kyle Lowry", "Pascal Siakam", "Fred VanVleet"]
  },
  {
    anio: 2018, campeon: "Warriors", ciudad: "Golden State", abbr: "GSW", color: "#1D428A", conferencia: "Oeste",
    finalista: "Cavaliers", resultado: "4-0", mvp: "Kevin Durant",
    roster: ["Stephen Curry", "Kevin Durant", "Klay Thompson", "Draymond Green"]
  },
  {
    anio: 2017, campeon: "Warriors", ciudad: "Golden State", abbr: "GSW", color: "#1D428A", conferencia: "Oeste",
    finalista: "Cavaliers", resultado: "4-1", mvp: "Kevin Durant",
    roster: ["Stephen Curry", "Kevin Durant", "Klay Thompson", "Draymond Green"]
  },
  {
    anio: 2016, campeon: "Cavaliers", ciudad: "Cleveland", abbr: "CLE", color: "#860038", conferencia: "Este",
    finalista: "Warriors", resultado: "4-3", mvp: "LeBron James",
    roster: ["LeBron James", "Kyrie Irving", "Kevin Love"]
  },
  {
    anio: 2015, campeon: "Warriors", ciudad: "Golden State", abbr: "GSW", color: "#1D428A", conferencia: "Oeste",
    finalista: "Cavaliers", resultado: "4-2", mvp: "Andre Iguodala",
    roster: ["Stephen Curry", "Klay Thompson", "Draymond Green", "Andre Iguodala"]
  },
  {
    anio: 2014, campeon: "Spurs", ciudad: "San Antonio", abbr: "SAS", color: "#C4CED4", conferencia: "Oeste",
    finalista: "Heat", resultado: "4-1", mvp: "Kawhi Leonard",
    roster: ["Tim Duncan", "Kawhi Leonard"]
  },
  {
    anio: 2013, campeon: "Heat", ciudad: "Miami", abbr: "MIA", color: "#98002E", conferencia: "Este",
    finalista: "Spurs", resultado: "4-3", mvp: "LeBron James",
    roster: ["LeBron James", "Dwyane Wade"]
  },
  {
    anio: 2012, campeon: "Heat", ciudad: "Miami", abbr: "MIA", color: "#98002E", conferencia: "Este",
    finalista: "Thunder", resultado: "4-1", mvp: "LeBron James",
    roster: ["LeBron James", "Dwyane Wade"]
  },
  {
    anio: 2011, campeon: "Mavericks", ciudad: "Dallas", abbr: "DAL", color: "#00538C", conferencia: "Oeste",
    finalista: "Heat", resultado: "4-2", mvp: "Dirk Nowitzki",
    roster: ["Dirk Nowitzki", "Jason Kidd"]
  },
  {
    anio: 2010, campeon: "Lakers", ciudad: "Los Angeles", abbr: "LAL", color: "#552583", conferencia: "Oeste",
    finalista: "Celtics", resultado: "4-3", mvp: "Kobe Bryant",
    roster: ["Kobe Bryant", "Pau Gasol"]
  },
  {
    anio: 2009, campeon: "Lakers", ciudad: "Los Angeles", abbr: "LAL", color: "#552583", conferencia: "Oeste",
    finalista: "Magic", resultado: "4-1", mvp: "Kobe Bryant",
    roster: ["Kobe Bryant", "Pau Gasol"]
  },
  {
    anio: 2008, campeon: "Celtics", ciudad: "Boston", abbr: "BOS", color: "#007A33", conferencia: "Este",
    finalista: "Lakers", resultado: "4-2", mvp: "Paul Pierce",
    roster: ["Paul Pierce", "Kevin Garnett", "Ray Allen"]
  },
  {
    anio: 2007, campeon: "Spurs", ciudad: "San Antonio", abbr: "SAS", color: "#C4CED4", conferencia: "Oeste",
    finalista: "Cavaliers", resultado: "4-0", mvp: "Tony Parker",
    roster: ["Tim Duncan", "Tony Parker"]
  },
  {
    anio: 2006, campeon: "Heat", ciudad: "Miami", abbr: "MIA", color: "#98002E", conferencia: "Este",
    finalista: "Mavericks", resultado: "4-2", mvp: "Dwyane Wade",
    roster: ["Dwyane Wade", "Shaquille O'Neal"]
  },
  {
    anio: 2005, campeon: "Spurs", ciudad: "San Antonio", abbr: "SAS", color: "#C4CED4", conferencia: "Oeste",
    finalista: "Pistons", resultado: "4-3", mvp: "Tim Duncan",
    roster: ["Tim Duncan", "Manu Ginobili"]
  },
  {
    anio: 2004, campeon: "Pistons", ciudad: "Detroit", abbr: "DET", color: "#C8102E", conferencia: "Este",
    finalista: "Lakers", resultado: "4-1", mvp: "Chauncey Billups",
    roster: ["Chauncey Billups", "Ben Wallace"]
  },
  {
    anio: 2003, campeon: "Spurs", ciudad: "San Antonio", abbr: "SAS", color: "#C4CED4", conferencia: "Oeste",
    finalista: "Nets", resultado: "4-2", mvp: "Tim Duncan",
    roster: ["Tim Duncan", "Tony Parker"]
  },
  {
    anio: 2002, campeon: "Lakers", ciudad: "Los Angeles", abbr: "LAL", color: "#552583", conferencia: "Oeste",
    finalista: "Nets", resultado: "4-0", mvp: "Shaquille O'Neal",
    roster: ["Shaquille O'Neal", "Kobe Bryant"]
  },
  {
    anio: 2001, campeon: "Lakers", ciudad: "Los Angeles", abbr: "LAL", color: "#552583", conferencia: "Oeste",
    finalista: "76ers", resultado: "4-1", mvp: "Shaquille O'Neal",
    roster: ["Shaquille O'Neal", "Kobe Bryant"]
  },
  {
    anio: 2000, campeon: "Lakers", ciudad: "Los Angeles", abbr: "LAL", color: "#552583", conferencia: "Oeste",
    finalista: "Pacers", resultado: "4-2", mvp: "Shaquille O'Neal",
    roster: ["Shaquille O'Neal", "Kobe Bryant"]
  },
  {
    anio: 1999, campeon: "Spurs", ciudad: "San Antonio", abbr: "SAS", color: "#C4CED4", conferencia: "Oeste",
    finalista: "Knicks", resultado: "4-1", mvp: "Tim Duncan",
    roster: ["Tim Duncan", "David Robinson"]
  },
  {
    anio: 1998, campeon: "Bulls", ciudad: "Chicago", abbr: "CHI", color: "#CE1141", conferencia: "Este",
    finalista: "Jazz", resultado: "4-2", mvp: "Michael Jordan",
    roster: ["Michael Jordan", "Scottie Pippen"]
  },
  {
    anio: 1997, campeon: "Bulls", ciudad: "Chicago", abbr: "CHI", color: "#CE1141", conferencia: "Este",
    finalista: "Jazz", resultado: "4-2", mvp: "Michael Jordan",
    roster: ["Michael Jordan", "Scottie Pippen"]
  },
  {
    anio: 1996, campeon: "Bulls", ciudad: "Chicago", abbr: "CHI", color: "#CE1141", conferencia: "Este",
    finalista: "SuperSonics", resultado: "4-2", mvp: "Michael Jordan",
    roster: ["Michael Jordan", "Scottie Pippen"]
  },
  {
    anio: 1995, campeon: "Rockets", ciudad: "Houston", abbr: "HOU", color: "#CE1141", conferencia: "Oeste",
    finalista: "Magic", resultado: "4-0", mvp: "Hakeem Olajuwon",
    roster: ["Hakeem Olajuwon", "Clyde Drexler"]
  },
  {
    anio: 1994, campeon: "Rockets", ciudad: "Houston", abbr: "HOU", color: "#CE1141", conferencia: "Oeste",
    finalista: "Knicks", resultado: "4-3", mvp: "Hakeem Olajuwon",
    roster: ["Hakeem Olajuwon"]
  },
  {
    anio: 1993, campeon: "Bulls", ciudad: "Chicago", abbr: "CHI", color: "#CE1141", conferencia: "Este",
    finalista: "Suns", resultado: "4-2", mvp: "Michael Jordan",
    roster: ["Michael Jordan", "Scottie Pippen"]
  },
  {
    anio: 1992, campeon: "Bulls", ciudad: "Chicago", abbr: "CHI", color: "#CE1141", conferencia: "Este",
    finalista: "Trail Blazers", resultado: "4-2", mvp: "Michael Jordan",
    roster: ["Michael Jordan", "Scottie Pippen"]
  },
  {
    anio: 1991, campeon: "Bulls", ciudad: "Chicago", abbr: "CHI", color: "#CE1141", conferencia: "Este",
    finalista: "Lakers", resultado: "4-1", mvp: "Michael Jordan",
    roster: ["Michael Jordan", "Scottie Pippen"]
  },
  {
    anio: 1990, campeon: "Pistons", ciudad: "Detroit", abbr: "DET", color: "#C8102E", conferencia: "Este",
    finalista: "Trail Blazers", resultado: "4-1", mvp: "Isiah Thomas",
    roster: ["Isiah Thomas", "Joe Dumars"]
  },
  {
    anio: 1989, campeon: "Pistons", ciudad: "Detroit", abbr: "DET", color: "#C8102E", conferencia: "Este",
    finalista: "Lakers", resultado: "4-0", mvp: "Joe Dumars",
    roster: ["Isiah Thomas", "Joe Dumars"]
  },
  {
    anio: 1988, campeon: "Lakers", ciudad: "Los Angeles", abbr: "LAL", color: "#552583", conferencia: "Oeste",
    finalista: "Pistons", resultado: "4-3", mvp: "James Worthy",
    roster: ["Magic Johnson", "James Worthy"]
  },
  {
    anio: 1987, campeon: "Lakers", ciudad: "Los Angeles", abbr: "LAL", color: "#552583", conferencia: "Oeste",
    finalista: "Celtics", resultado: "4-2", mvp: "Magic Johnson",
    roster: ["Magic Johnson", "Kareem Abdul-Jabbar"]
  },
  {
    anio: 1986, campeon: "Celtics", ciudad: "Boston", abbr: "BOS", color: "#007A33", conferencia: "Este",
    finalista: "Rockets", resultado: "4-2", mvp: "Larry Bird",
    roster: ["Larry Bird", "Kevin McHale"]
  },
  {
    anio: 1985, campeon: "Lakers", ciudad: "Los Angeles", abbr: "LAL", color: "#552583", conferencia: "Oeste",
    finalista: "Celtics", resultado: "4-2", mvp: "Kareem Abdul-Jabbar",
    roster: ["Magic Johnson", "Kareem Abdul-Jabbar"]
  },
  {
    anio: 1984, campeon: "Celtics", ciudad: "Boston", abbr: "BOS", color: "#007A33", conferencia: "Este",
    finalista: "Lakers", resultado: "4-3", mvp: "Larry Bird",
    roster: ["Larry Bird", "Kevin McHale"]
  },
  {
    anio: 1983, campeon: "76ers", ciudad: "Philadelphia", abbr: "PHI", color: "#006BB6", conferencia: "Este",
    finalista: "Lakers", resultado: "4-0", mvp: "Moses Malone",
    roster: ["Moses Malone", "Julius Erving"]
  },
  {
    anio: 1982, campeon: "Lakers", ciudad: "Los Angeles", abbr: "LAL", color: "#552583", conferencia: "Oeste",
    finalista: "76ers", resultado: "4-2", mvp: "Magic Johnson",
    roster: ["Magic Johnson", "Kareem Abdul-Jabbar"]
  },
  {
    anio: 1981, campeon: "Celtics", ciudad: "Boston", abbr: "BOS", color: "#007A33", conferencia: "Este",
    finalista: "Rockets", resultado: "4-2", mvp: "Cedric Maxwell",
    roster: ["Larry Bird", "Cedric Maxwell"]
  },
  {
    anio: 1980, campeon: "Lakers", ciudad: "Los Angeles", abbr: "LAL", color: "#552583", conferencia: "Oeste",
    finalista: "76ers", resultado: "4-2", mvp: "Magic Johnson",
    roster: ["Magic Johnson", "Kareem Abdul-Jabbar"]
  },
  {
    anio: 1979, campeon: "SuperSonics", ciudad: "Seattle", abbr: "SEA", color: "#00653A", conferencia: "Oeste",
    finalista: "Bullets", resultado: "4-1", mvp: "Dennis Johnson",
    roster: ["Dennis Johnson", "Gus Williams"]
  },
  {
    anio: 1978, campeon: "Bullets", ciudad: "Washington", abbr: "WSB", color: "#002B5C", conferencia: "Este",
    finalista: "SuperSonics", resultado: "4-3", mvp: "Wes Unseld",
    roster: ["Wes Unseld", "Elvin Hayes"]
  },
  {
    anio: 1977, campeon: "Trail Blazers", ciudad: "Portland", abbr: "POR", color: "#E03A3E", conferencia: "Oeste",
    finalista: "76ers", resultado: "4-2", mvp: "Bill Walton",
    roster: ["Bill Walton"]
  },
  {
    anio: 1976, campeon: "Celtics", ciudad: "Boston", abbr: "BOS", color: "#007A33", conferencia: "Este",
    finalista: "Suns", resultado: "4-2", mvp: "Jo Jo White",
    roster: ["Jo Jo White", "Dave Cowens"]
  },
  {
    anio: 1975, campeon: "Warriors", ciudad: "Golden State", abbr: "GSW", color: "#1D428A", conferencia: "Oeste",
    finalista: "Bullets", resultado: "4-0", mvp: "Rick Barry",
    roster: ["Rick Barry"]
  },
  {
    anio: 1974, campeon: "Celtics", ciudad: "Boston", abbr: "BOS", color: "#007A33", conferencia: "Este",
    finalista: "Bucks", resultado: "4-3", mvp: "John Havlicek",
    roster: ["John Havlicek", "Dave Cowens"]
  },
  {
    anio: 1973, campeon: "Knicks", ciudad: "New York", abbr: "NYK", color: "#F58426", conferencia: "Este",
    finalista: "Lakers", resultado: "4-1", mvp: "Willis Reed",
    roster: ["Willis Reed", "Walt Frazier"]
  },
  {
    anio: 1972, campeon: "Lakers", ciudad: "Los Angeles", abbr: "LAL", color: "#552583", conferencia: "Oeste",
    finalista: "Knicks", resultado: "4-1", mvp: "Wilt Chamberlain",
    roster: ["Wilt Chamberlain", "Jerry West"]
  },
  {
    anio: 1971, campeon: "Bucks", ciudad: "Milwaukee", abbr: "MIL", color: "#00471B", conferencia: "Oeste",
    finalista: "Bullets", resultado: "4-0", mvp: "Lew Alcindor (Kareem Abdul-Jabbar)",
    roster: ["Kareem Abdul-Jabbar", "Oscar Robertson"]
  },
  {
    anio: 1970, campeon: "Knicks", ciudad: "New York", abbr: "NYK", color: "#F58426", conferencia: "Este",
    finalista: "Lakers", resultado: "4-3", mvp: "Willis Reed",
    roster: ["Willis Reed", "Walt Frazier"]
  },
  {
    anio: 1969, campeon: "Celtics", ciudad: "Boston", abbr: "BOS", color: "#007A33", conferencia: "Este",
    finalista: "Lakers", resultado: "4-3", mvp: "Jerry West",
    roster: ["Bill Russell", "John Havlicek"]
  },
  {
    anio: 1968, campeon: "Celtics", ciudad: "Boston", abbr: "BOS", color: "#007A33", conferencia: "Este",
    finalista: "Lakers", resultado: "4-2", mvp: "Bill Russell",
    roster: ["Bill Russell", "John Havlicek"]
  },
  {
    anio: 1967, campeon: "76ers", ciudad: "Philadelphia", abbr: "PHI", color: "#006BB6", conferencia: "Este",
    finalista: "Warriors", resultado: "4-2", mvp: "N/A (premio no existia)",
    roster: ["Wilt Chamberlain", "Hal Greer"]
  },
  {
    anio: 1966, campeon: "Celtics", ciudad: "Boston", abbr: "BOS", color: "#007A33", conferencia: "Este",
    finalista: "Lakers", resultado: "4-3", mvp: "N/A (premio no existia)",
    roster: ["Bill Russell", "John Havlicek"]
  },
  {
    anio: 1965, campeon: "Celtics", ciudad: "Boston", abbr: "BOS", color: "#007A33", conferencia: "Este",
    finalista: "Lakers", resultado: "4-1", mvp: "N/A (premio no existia)",
    roster: ["Bill Russell", "Sam Jones"]
  },
  {
    anio: 1964, campeon: "Celtics", ciudad: "Boston", abbr: "BOS", color: "#007A33", conferencia: "Este",
    finalista: "Warriors", resultado: "4-1", mvp: "N/A (premio no existia)",
    roster: ["Bill Russell", "Sam Jones"]
  },
  {
    anio: 1963, campeon: "Celtics", ciudad: "Boston", abbr: "BOS", color: "#007A33", conferencia: "Este",
    finalista: "Lakers", resultado: "4-2", mvp: "N/A (premio no existia)",
    roster: ["Bill Russell", "Bob Cousy"]
  },
  {
    anio: 1962, campeon: "Celtics", ciudad: "Boston", abbr: "BOS", color: "#007A33", conferencia: "Este",
    finalista: "Lakers", resultado: "4-3", mvp: "N/A (premio no existia)",
    roster: ["Bill Russell", "Bob Cousy"]
  },
  {
    anio: 1961, campeon: "Celtics", ciudad: "Boston", abbr: "BOS", color: "#007A33", conferencia: "Este",
    finalista: "Hawks", resultado: "4-1", mvp: "N/A (premio no existia)",
    roster: ["Bill Russell", "Bob Cousy"]
  },
  {
    anio: 1960, campeon: "Celtics", ciudad: "Boston", abbr: "BOS", color: "#007A33", conferencia: "Este",
    finalista: "Hawks", resultado: "4-3", mvp: "N/A (premio no existia)",
    roster: ["Bill Russell", "Bob Cousy"]
  },
  {
    anio: 1959, campeon: "Celtics", ciudad: "Boston", abbr: "BOS", color: "#007A33", conferencia: "Este",
    finalista: "Lakers", resultado: "4-0", mvp: "N/A (premio no existia)",
    roster: ["Bill Russell", "Bob Cousy"]
  },
  {
    anio: 1958, campeon: "Hawks", ciudad: "St. Louis", abbr: "STL", color: "#E03A3E", conferencia: "Oeste",
    finalista: "Celtics", resultado: "4-2", mvp: "N/A (premio no existia)",
    roster: ["Bob Pettit"]
  },
  {
    anio: 1957, campeon: "Celtics", ciudad: "Boston", abbr: "BOS", color: "#007A33", conferencia: "Este",
    finalista: "Hawks", resultado: "4-3", mvp: "N/A (premio no existia)",
    roster: ["Bill Russell", "Bob Cousy"]
  },
  {
    anio: 1956, campeon: "Warriors", ciudad: "Philadelphia", abbr: "PHW", color: "#1D428A", conferencia: "Este",
    finalista: "Pistons", resultado: "4-1", mvp: "N/A (premio no existia)",
    roster: ["Paul Arizin", "Neil Johnston"]
  },
  {
    anio: 1955, campeon: "Nationals", ciudad: "Syracuse", abbr: "SYR", color: "#006BB6", conferencia: "Este",
    finalista: "Pistons", resultado: "4-3", mvp: "N/A (premio no existia)",
    roster: ["Dolph Schayes"]
  },
  {
    anio: 1954, campeon: "Lakers", ciudad: "Minneapolis", abbr: "MNL", color: "#552583", conferencia: "Oeste",
    finalista: "Nationals", resultado: "4-3", mvp: "N/A (premio no existia)",
    roster: ["George Mikan", "Jim Pollard"]
  },
  {
    anio: 1953, campeon: "Lakers", ciudad: "Minneapolis", abbr: "MNL", color: "#552583", conferencia: "Oeste",
    finalista: "Knicks", resultado: "4-1", mvp: "N/A (premio no existia)",
    roster: ["George Mikan", "Jim Pollard"]
  },
  {
    anio: 1952, campeon: "Lakers", ciudad: "Minneapolis", abbr: "MNL", color: "#552583", conferencia: "Oeste",
    finalista: "Knicks", resultado: "4-3", mvp: "N/A (premio no existia)",
    roster: ["George Mikan", "Jim Pollard", "Vern Mikkelsen"]
  },
  {
    anio: 1951, campeon: "Royals", ciudad: "Rochester", abbr: "ROC", color: "#5A2D81", conferencia: "Oeste",
    finalista: "Knicks", resultado: "4-3", mvp: "N/A (premio no existia)",
    roster: ["Bob Davies", "Arnie Risen"]
  },
  {
    anio: 1950, campeon: "Lakers", ciudad: "Minneapolis", abbr: "MNL", color: "#552583", conferencia: "Oeste",
    finalista: "Nationals", resultado: "4-2", mvp: "N/A (premio no existia)",
    roster: ["George Mikan", "Jim Pollard"]
  },
  {
    anio: 1949, campeon: "Lakers", ciudad: "Minneapolis", abbr: "MNL", color: "#552583", conferencia: "Oeste",
    finalista: "Capitols", resultado: "4-2", mvp: "N/A (premio no existia)",
    roster: ["George Mikan", "Jim Pollard"]
  },
  {
    anio: 1948, campeon: "Bullets (original)", ciudad: "Baltimore", abbr: "BAL", color: "#002B5C", conferencia: "Oeste",
    finalista: "Warriors", resultado: "4-2", mvp: "N/A (premio no existia)",
    roster: ["Buddy Jeannette", "Paul Hoffman"]
  },
  {
    anio: 1947, campeon: "Warriors", ciudad: "Philadelphia", abbr: "PHW", color: "#1D428A", conferencia: "Este",
    finalista: "Stags", resultado: "4-1", mvp: "N/A (premio no existia)",
    roster: ["Joe Fulks"]
  },
];