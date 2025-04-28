document.querySelectorAll('.faq-question').forEach(item => {
    item.addEventListener('click', () => {
        const answer = item.nextElementSibling;
        const icon = item.querySelector('.dropdown-icon');
        answer.style.display = answer.style.display === 'block' ? 'none' : 'block';
        item.classList.toggle('active');
    });
});