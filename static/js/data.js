// Estos mismos datos viven en database/nba_wiki.sql
// En la version estatica se simula la BD con este arreglo.
// En la version JavaWeb, esto lo entrega un Servlet leyendo MySQL via JDBC.

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
  }
];
