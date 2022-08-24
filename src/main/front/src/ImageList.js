import Image from "./component/Image";
import {useState} from "react";

function ImageList(props) {
    const defaultProps ={
        data:[]
    }

    const {data} = props;
    console.log(data);
    const list = data.map(
        (info,i) => (<Image url={info.imageURL} key={i}/>)
    );

    return (
        <div>
            {list}
        </div>
    );
}
export default ImageList;

