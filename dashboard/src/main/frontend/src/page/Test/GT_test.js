<script>
    function handleClick() {
    const message = {
    msgCode : 'CLICK_OBJECT',
    data : {
}
};
    window.parent.postMessage(message,'*');
}
    function handleClick2() {
    const message = {
    msgCode : 'CLICK_OBJECT2',
    data : {
}
};
    window.parent.postMessage(message,'*');
}
    window.addEventListener('message',(e)=> {
    // console.log(e.data);
    let dom = document.getElementById("inputTest");
    dom.style.color="red"
    dom.style.fontSize="30px"
    dom.innerHTML=e.data;
})
    window.addEventListener('message',(e)=> {
    // let data = JSON.parse(e.data);
    let dom = document.getElementById("inputTest");
    dom.style.color="red"
    dom.style.fontSize="30px"
    dom.innerHTML= e.data.data.toString();
})
</script>