import {Paper, Stack} from "@mui/material";
import Image from "./Image";

function UploadedImages(props) {
    return (
        <Paper style={
            {
                background: "aliceblue",
                height: 300,
                alignContent: "center"
            }
        }>
            <Stack direction={"row"} spacing={2} justifyContent={"center"} alignContent={"center"} overflow={"auto"}>
                {props.images && props.images.map((item, idx) => (
                        <Image id={idx} imageURL= {item.imageURL} thumbnailURL={item.thumbnailURL} removeImage={props.removeImage}/>
                    )
                )}
            </Stack>
        </Paper>
    )
}

export default UploadedImages;
