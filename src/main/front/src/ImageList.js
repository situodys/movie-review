import Image from "./Image";
import {useState} from "react";

function ImageList(props) {
    const defaultProps ={
        data:[]
    }

    const {data} = props;
    console.log(data);
    const list = data.map(
        info => (<Image url={info}/>)
    );

    return (
        <div>
            {list}
        </div>
    );
}
export default ImageList;

