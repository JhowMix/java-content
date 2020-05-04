function buildTable(data) {
	data.forEach(product => {
		let date = new Date(product.validateDate).toLocaleDateString('pt-BR')

		let html =
			`<tr id="${product.id}">` +
				"<td>" + product.name + "</td>" +
				"<td>R$" + product.price + "</td>" +
				"<td>" + product.amount + "</td>" +
				"<td>" + date + "</td>" +
				"<td>"+
					`<a class='waves-effect waves-light btn-small' href='form.html?id=${product.id}'>Modificar</a>` +
					"<a id='delete-product' class='waves-effect waves-light btn-small'>Deletar</a>" +
				"</td>" +
			"</tr>"

		$(".products-table tbody")
			.append(html)

		$(`#${product.id} #delete-product`).click(_ => {
			fetch(
				`api/products/${product.id}`,
				{
					method: 'DELETE',
					mode: 'cors',
				}
			).then(
				response => {
					return response.status;
				}
			).then(
				response => {
					if (response === 200) {
						$(`#${product.id}`).remove()
					} else {
						console.log(`Error code status ${product.id}`)
					}
				}
			);
		})
	})
}

$.get("api/products", (data, status) => {
	if (status === 'success') 
		buildTable(data)
})

$("#fetch-product").keypress(x => {
	let keyCode = x.keyCode ? x.keyCode : x.which;
	if (keyCode === 13) {
		let value = $('#fetch-product').val();
		$.get(`api/products/search=${value}`, (product, status) => {
			if (status === 'success') {
				let date = new Date(product.validateDate).toLocaleDateString('pt-BR')
				let html = 				
				`<tr id="${product.id}">` +
					"<td>" + product.name + "</td>" +
					"<td>R$" + product.price + "</td>" +
					"<td>" + product.amount + "</td>" +
					"<td>" + date + "</td>" +
					"<td>" + 
						"<a id='update-product' class='waves-effect waves-light btn-small' href='form.html'>Modificar</a>" +
						"<a id='delete-product' class='waves-effect waves-light btn-small'>Deletar</a>"+
					"</td>" +
				"</tr>"
				$(".products-table tbody").empty()
				$(".products-table tbody").append(html)
			}
		})
	}
})

$("#refresh-table").click(x => {
	$(".products-table tbody").empty()
	$.get("http://localhost:8080/warehouse-0.0.1/api/products", (data, status) => {
		if (status === 'success') 
			buildTable(data)
	})
})