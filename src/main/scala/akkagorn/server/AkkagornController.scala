package akkagorn.server

import akkagorn.api._

class AkkagornController[F[_]]() {
    def register(registerDto: RegisterDto): F[Either[ApiError, Unit]] = {
        ???
    }

    def publish(publishDto: PublishDto): F[Either[ApiError, Unit]] = {
        ???
    }
}
