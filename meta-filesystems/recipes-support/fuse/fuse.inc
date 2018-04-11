SUMMARY = "Implementation of a fully functional filesystem in a userspace program"
DESCRIPTION = "FUSE (Filesystem in Userspace) is a simple interface for userspace \
               programs to export a virtual filesystem to the Linux kernel. FUSE \
               also aims to provide a secure method for non privileged users to \
               create and mount their own filesystem implementations. \
              "
HOMEPAGE = "https://github.com/libfuse/libfuse"
SECTION = "libs"
LICENSE = "GPLv2 & LGPLv2"

S = "${WORKDIR}/git"

DEPENDS = "gettext-native udev"

PACKAGES =+ "${PN}-utils-dbg ${PN}-utils"

RDEPENDS_${PN}_class-target = "fuse-common"
RRECOMMENDS_${PN}_class-target = "kernel-module-fuse ${PN}-utils"

# Forbid auto-renaming to libfuse-utils
FILES_${PN}-utils = "${bindir} ${base_sbindir}"
FILES_${PN}-utils-dbg = "${bindir}/.debug ${base_sbindir}/.debug"
DEBIAN_NOAUTONAME_${PN}-utils = "1"
DEBIAN_NOAUTONAME_${PN}-utils-dbg = "1"

BBCLASSEXTEND = "native nativesdk"

do_install_append() {
    rm -rf ${D}${base_prefix}/dev
}
