async function createSubscription(token) {
    const response = await fetch('/create-subscription', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ token: token.id }),
    });

    if (response.ok) {
        console.log('Subscription created successfully');
    } else {
        console.log('Failed to create subscription');
    }
}