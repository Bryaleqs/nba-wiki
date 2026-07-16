// Renderiza el salon de banderines y las tarjetas de equipos campeones.

const raftersEl = document.getElementById('rafters');
const gridEl = document.getElementById('grid');
const filtersEl = document.getElementById('filters');
const decadeFiltersEl = document.getElementById('filtersDecada');

let filtroActual = 'todos';
let decadaActual = 'todos';

function crearBanner(c, index) {
  const btn = document.createElement('button');
  btn.className = 'banner fade-in-up';
  btn.style.animationDelay = (index * 0.02) + 's';
  btn.dataset.anio = c.anio;
  btn.style.setProperty('--team-color', c.color);
  btn.innerHTML = `
    <div class="flag">
      <span class="year">${c.anio}</span>
      <span class="abbr">${c.abbr}</span>
    </div>
    <div class="label">${c.campeon}</div>
  `;
  btn.addEventListener('click', () => {
    const card = document.getElementById('card-' + c.anio);
    if (card) {
      card.scrollIntoView({ behavior: 'smooth', block: 'center' });
      document.querySelectorAll('.card').forEach(el => el.classList.remove('highlight'));
      card.classList.add('highlight');
      setTimeout(() => card.classList.remove('highlight'), 1600);
    }
  });
  return btn;
}

function crearCard(c, index) {
  const div = document.createElement('div');
  div.className = 'card fade-in-up';
  div.style.animationDelay = ((index % 12) * 0.04) + 's';
  div.id = 'card-' + c.anio;
  div.style.setProperty('--team-color', c.color);
  div.innerHTML = `
    <div class="top">
      <div class="badge">
        <img class="badge-logo" src="img/logos/${c.abbr}.png" alt=""
             onload="this.style.display='block'; this.nextElementSibling.style.display='none';"
             onerror="this.style.display='none';">
        <span class="badge-fallback">${c.abbr}</span>
      </div>
      <div>
        <h3>${c.campeon}</h3>
        <div class="year-tag">${c.ciudad} &middot; Temporada ${c.anio}</div>
      </div>
    </div>
    <div class="row"><span>Conferencia</span><b>${c.conferencia}</b></div>
    <div class="row"><span>Rival en la final</span><b>${c.finalista}</b></div>
    <div class="row"><span>Resultado de la serie</span><b>${c.resultado}</b></div>
    <div class="roster">
      ${c.roster.map(j => `<span>${j}</span>`).join('')}
    </div>
    <div class="mvp-tag">MVP Finales: ${c.mvp}</div>
  `;
  return div;
}

function render() {
  raftersEl.innerHTML = '';
  gridEl.innerHTML = '';

  let datos = filtroActual === 'todos'
    ? CAMPEONATOS
    : CAMPEONATOS.filter(c => c.conferencia === filtroActual);

  if (decadaActual !== 'todos') {
    const inicio = parseInt(decadaActual, 10);
    datos = datos.filter(c => c.anio >= inicio && c.anio < inicio + 10);
  }

  datos.forEach((c, i) => {
    raftersEl.appendChild(crearBanner(c, i));
    gridEl.appendChild(crearCard(c, i));
  });
}

filtersEl.addEventListener('click', (e) => {
  const btn = e.target.closest('button');
  if (!btn) return;
  filtersEl.querySelectorAll('button').forEach(b => b.classList.remove('active'));
  btn.classList.add('active');
  filtroActual = btn.dataset.filter;
  render();
});

if (decadeFiltersEl) {
  decadeFiltersEl.addEventListener('click', (e) => {
    const btn = e.target.closest('button');
    if (!btn) return;
    decadeFiltersEl.querySelectorAll('button').forEach(b => b.classList.remove('active'));
    btn.classList.add('active');
    decadaActual = btn.dataset.decada;
    render();
  });
}

render();
