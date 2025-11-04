let stompClient;
function connectWs(restaurantId) {
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        stompClient.subscribe(`/topic/orders/${restaurantId}`, function (msg) {
            const order = JSON.parse(msg.body);
            const notif = document.getElementById('notifications');
            notif.innerHTML += `<p>New order received: #${order.id}</p>`;
        });
    });
}
