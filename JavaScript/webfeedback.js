document.querySelectorAll('.star-rating input[type="radio"]').forEach((radio) => {
    radio.addEventListener('change', function() {
        const allLabels = document.querySelectorAll('.star-rating label');
        allLabels.forEach(label => label.style.color = '#ddd'); // Reset all labels to default color

        let currentLabel = this.nextElementSibling;
        while (currentLabel) {
            currentLabel.style.color = '#f5b301'; // Change color of current and previous labels
            currentLabel = currentLabel.previousElementSibling;
            if (currentLabel && currentLabel.tagName !== 'LABEL') {
                currentLabel = currentLabel.previousElementSibling;
            }
        }
    });
});


/*
const stars = document.querySelectorAll('.star-rating label');

stars.forEach((star, index) => {
    star.addEventListener('mouseover', () => {
        for (let i = 0; i <= index; i++) {
            stars[i].style.color = '#f5b301';
        }
    });

    star.addEventListener('mouseout', () => {
        stars.forEach((star, i) => {
            if (!stars[i].previousElementSibling.checked) {
                stars[i].style.color = '#ddd';
            } else {
                stars[i].style.color = '#f5b301';
            }
        });
    });

    star.addEventListener('click', () => {
        for (let i = 0; i < stars.length; i++) {
            if (i <= index) {
                stars[i].previousElementSibling.checked = true; // Select stars to the left
                stars[i].style.color = '#f5b301'; // Highlight selected stars
            } else {
                stars[i].previousElementSibling.checked = false; // Deselect stars to the right
                stars[i].style.color = '#ddd'; // Reset color for stars to the right
            }
        }
    });

});
*/