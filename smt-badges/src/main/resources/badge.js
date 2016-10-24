function getWidth(id) {
  return document.getElementById(id).getBBox().width;
}

function pathPoints(x, y, h, v, H) {
  return 'M' + x + ' ' + y + 'h' + h + 'v' + v + 'H' + H + 'z';
}

function setAttribute(id, name, value) {
  document.getElementById(id).setAttribute(name, value);
}

function setWidth(id, width) {
  setAttribute(id, 'width', width);
}

function setPath(id, x, y, h, v, H) {
  setAttribute(id, 'd', pathPoints(x, y, h, v, H));
}

function setX(id, x) {
  setAttribute(id, 'x', x);
}

function updateBadgeWidths(badgeHeight, textPadding) {
  var containerPadding = (textPadding * 2);

  var subjectTextWidth = getWidth('smt-badge-subject');
  var statusTextWidth = getWidth('smt-badge-status');

  var subjectWidth = subjectTextWidth + containerPadding;
  var statusWidth = statusTextWidth + containerPadding;
  var badgeWidth = subjectWidth + statusWidth;

  setWidth('smt-svg', badgeWidth);
  setWidth('smt-badge-subject-rectangle', badgeWidth);
  setPath('smt-badge-subject-path', 0, 0, subjectWidth, badgeHeight, 0);
  setPath('smt-badge-status-path', subjectWidth, 0, statusWidth, badgeHeight, subjectWidth);
  setPath('smt-badge-gradient-path', 0, 0, badgeWidth, badgeHeight, 0);
  setX('smt-badge-subject', textPadding);
  setX('smt-badge-status', subjectWidth + textPadding);
}

if (document.getElementById('smt-svg')) {
  updateBadgeWidths(20, 5);
}
