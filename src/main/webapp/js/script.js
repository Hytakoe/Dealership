// Обработка вкладок
document.addEventListener('DOMContentLoaded', function() {
    // Табы
    const tabBtns = document.querySelectorAll('.tab-btn');
    tabBtns.forEach(btn => {
        btn.addEventListener('click', function() {
            const tabId = this.dataset.tab;

            tabBtns.forEach(b => b.classList.remove('active'));
            this.classList.add('active');

            document.querySelectorAll('.tab-content').forEach(content => {
                content.classList.remove('active');
            });

            document.getElementById(tabId).classList.add('active');
        });
    });

    // Переключение типа автомобиля
    const vehicleTypeSelect = document.getElementById('vehicleType');
    const carFields = document.getElementById('car-fields');
    const truckFields = document.getElementById('truck-fields');

    if (vehicleTypeSelect) {
        vehicleTypeSelect.addEventListener('change', function() {
            if (this.value === 'CAR') {
                if (carFields) carFields.style.display = 'block';
                if (truckFields) truckFields.style.display = 'none';
            } else {
                if (carFields) carFields.style.display = 'none';
                if (truckFields) truckFields.style.display = 'block';
            }
        });
    }

    // Переключение типа покупателя при продаже
    const customerOptionRadios = document.querySelectorAll('input[name="customerOption"]');
    const existingFields = document.getElementById('existing-customer-fields');
    const newFields = document.getElementById('new-customer-fields');

    if (customerOptionRadios.length > 0) {
        customerOptionRadios.forEach(radio => {
            radio.addEventListener('change', function() {
                if (this.value === 'existing') {
                    if (existingFields) existingFields.style.display = 'block';
                    if (newFields) newFields.style.display = 'none';
                } else {
                    if (existingFields) existingFields.style.display = 'none';
                    if (newFields) newFields.style.display = 'block';
                }
            });
        });
    }

    // Автосохранение формы
    const forms = document.querySelectorAll('form');
    forms.forEach(form => {
        form.addEventListener('submit', function() {
            localStorage.removeItem('formData_' + form.id);
        });

        const inputs = form.querySelectorAll('input, select, textarea');
        inputs.forEach(input => {
            input.addEventListener('input', function() {
                saveFormData(form.id);
            });
        });

        loadFormData(form.id);
    });

    function saveFormData(formId) {
        const form = document.getElementById(formId);
        if (!form) return;

        const formData = {};
        const inputs = form.querySelectorAll('input, select, textarea');
        inputs.forEach(input => {
            if (input.name && input.type !== 'submit' && input.type !== 'button') {
                formData[input.name] = input.value;
            }
        });
        localStorage.setItem('formData_' + formId, JSON.stringify(formData));
    }

    function loadFormData(formId) {
        const saved = localStorage.getItem('formData_' + formId);
        if (saved) {
            const formData = JSON.parse(saved);
            const form = document.getElementById(formId);
            if (form) {
                Object.keys(formData).forEach(key => {
                    const input = form.querySelector(`[name="${key}"]`);
                    if (input && !input.value) {
                        input.value = formData[key];
                    }
                });
            }
        }
    }

    // Подтверждение удаления
    const deleteForms = document.querySelectorAll('.delete-form');
    deleteForms.forEach(form => {
        form.addEventListener('submit', function(e) {
            if (!confirm('Вы уверены, что хотите удалить?')) {
                e.preventDefault();
            }
        });
    });

    // Валидация форм
    const addVehicleForm = document.getElementById('addVehicleForm');
    if (addVehicleForm) {
        addVehicleForm.addEventListener('submit', function(e) {
            const vehicleType = document.getElementById('vehicleType').value;
            let isValid = true;

            if (vehicleType === 'CAR') {
                const doorCount = document.querySelector('input[name="doorCount"]');
                const passengerCapacity = document.querySelector('input[name="passengerCapacity"]');

                if (doorCount && (!doorCount.value || doorCount.value < 2 || doorCount.value > 5)) {
                    alert('Количество дверей должно быть от 2 до 5');
                    isValid = false;
                }

                if (passengerCapacity && (!passengerCapacity.value || passengerCapacity.value < 2 || passengerCapacity.value > 9)) {
                    alert('Количество мест должно быть от 2 до 9');
                    isValid = false;
                }
            }

            if (!isValid) {
                e.preventDefault();
            }
        });
    }

    // Добавление анимации
    const cards = document.querySelectorAll('.card, .report-card');
    cards.forEach(card => {
        card.addEventListener('mouseenter', function() {
            this.style.transform = 'translateY(-5px)';
        });

        card.addEventListener('mouseleave', function() {
            this.style.transform = 'translateY(0)';
        });
    });
});