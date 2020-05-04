var id_param = new URLSearchParams(window.location.search)

function toServer(method) {
  let url = (method == 'PUT') ? $('#id').text(): '';

  $('#save-product').click( _=> {
    let product = {
      id: parseInt($('#id').text()),
      name: $('#name').val(),
      price: parseFloat($('#price').val().replace(/,/, '.')).toFixed(2),
      amount: $('#amount').val(),
      validateDate: $('#validateDate').val()
    }

    fetch(
      `api/products/${url}`,
      {
        method: method,
        mode: 'cors',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(product),
      }
    ).then(
      response => {
        return response.status;
      }
    ).then(
      response => {
        if (response === 201 || response === 200) {
          window.location.replace('index.html')
        } else {
          console.log(`Error code status ${response}`)
        }
      }
    );
  })
}

if(id_param.has('id')) {
  $.get(`api/products/${id_param.get('id')}`, (product, status) => {
    if (status === 'success') {
      $('#id').append(product.id)
      $('#name').val(product.name)
      $('#price').val(product.price)
      $('#amount').val(product.amount)
      $('#validateDate').val(product.validateDate)
    }

    toServer('PUT')
  })
} else {
 toServer('POST')
}

$(document).ready(function(){
  $('#price').mask('#.##0,00', {reverse: true});
});
  