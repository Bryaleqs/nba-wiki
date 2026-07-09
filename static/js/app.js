// Renderiza el salon de banderines y las tarjetas de equipos campeones.

const raftersEl = document.getElementById('rafters');
const gridEl = document.getElementById('grid');
const filtersEl = document.getElementById('filters');

let filtroActual = 'todos';

function crearBanner(c) {
  const btn = document.createElement('button');
  btn.className = 'banner';
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

function crearCard(c) {
  const div = document.createElement('div');
  div.className = 'card';
  div.id = 'card-' + c.anio;
  div.style.setProperty('--team-color', c.color);
  div.innerHTML = `
    <div class="top">
      <div class="badge">${c.abbr}</div>
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

  const datos = filtroActual === 'todos'
    ? CAMPEONATOS
    : CAMPEONATOS.filter(c => c.conferencia === filtroActual);

  datos.forEach(c => {
    raftersEl.appendChild(crearBanner(c));
    gridEl.appendChild(crearCard(c));
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

render();
