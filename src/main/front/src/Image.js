function Image(props){

    console.log(props.name)
    return <img src={props.name} />
}

export default Image;