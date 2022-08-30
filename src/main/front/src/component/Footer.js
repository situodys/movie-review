import {Typography} from "@mui/material";

export default function Footer() {
    return(
        <Typography variant="body2" color={"textSecondary"} align={"center"}>
            {"Copyright "}
            movie-review, {new Date().getFullYear()}
            {"."}
        </Typography>
    );
}